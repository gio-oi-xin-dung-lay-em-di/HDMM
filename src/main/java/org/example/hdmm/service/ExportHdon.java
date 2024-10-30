package org.example.hdmm.service;
import com.github.pjfanning.xlsx.StreamingReader;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.hdmm.dto.FindHoaDonDTO;
import org.example.hdmm.dto.MapIDTen;
import org.example.hdmm.models.HoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ExportHdon {


    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;



    private void writeHeader(Sheet sheet) {

        Row row = sheet.createRow(0);
        createCell(row, 0, "id");
        createCell(row, 1, "Mã hóa đơn");
        createCell(row, 2, "Mã loại hóa đơn");
        createCell(row, 3, "Tên loại hóa đơn");
        createCell(row, 4, "Ký hiệu hóa đơn");
        createCell(row, 5, "Số hóa đơn");
        createCell(row, 6, "Mst người bán");
        createCell(row, 7, "Tên người bán");
        createCell(row, 8, "Mst người mua");
        createCell(row, 9, "Tên người mua");
        createCell(row, 10, "Thời gian lập");
        createCell(row, 11, "Mã trạng thái");
        createCell(row, 12, "Tên trạng thái");
        createCell(row, 13, "Mã loại NNT");
        createCell(row, 14, "Mã quan thuế");
        createCell(row, 15, "Tên cơ quan thuế");
        createCell(row, 16, "Đánh dấu đủ/không đủ ĐK");
        createCell(row, 17, "Lý do");
        createCell(row, 18, "Loại bỏ");
        for (int i = 0; i <= 16; i++) {
            sheet.setColumnWidth(i, 4000);
        }
    }



    void createCell(Row row, int column, String value) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
    }

    void createRow(Sheet sheet, int rowNum,HoaDon record) {
        Row row = sheet.createRow(rowNum);
        createCell(row,0, record.getId().toString());
        createCell(row,1, record.getMahd());
        createCell(row, 2, record.getKhmhd().toString());
        createCell(row,3, MapIDTen.getTenLoaiHdon(record.getKhmhd()));
        createCell(row, 4, record.getKhhd());//
        createCell(row, 5, record.getSohd());//
        createCell(row, 6, record.getNbmst());//
        createCell(row, 7, record.getNbTen());//
        createCell(row, 8, record.getNmmst());//
        createCell(row, 9, record.getNmTen());//
        createCell(row, 10, record.getTdlap().toString());//
        createCell(row, 11, record.getTthai());//
        createCell(row, 12, MapIDTen.getTenTThai(record.getTthai()));//
        createCell(row, 13, record.getLoaiNnt().toString());//
        createCell(row, 14, record.getCoQuanThue().getCqt());//
        createCell(row, 15, record.getCoQuanThue().getTenCQT());//
        createCell(row, 16, record.getIsQualified().toString());//
        createCell(row, 17, record.getLydo());
    }

    private void write(List<HoaDon> listHoaDon,Sheet sheet) {
        int rowCount = 1;
        for (HoaDon record : listHoaDon) {
            createRow(sheet, rowCount++, record);
        }
    }


    public void generateExcelFile(HttpServletResponse response,FindHoaDonDTO dto) throws IOException {
        System.out.println(LocalDateTime.now());

        SXSSFWorkbook workbook = new SXSSFWorkbook();
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        int total = hoaDonService.countTraCuuHoaDon(dto);
        int page =(int) Math.ceil((double) total / 10000);
        List<Sheet> sheets = new ArrayList<>();
        for(int i =0; i < page;i++){
            Sheet sheet =  workbook.createSheet();
            sheets.add(sheet);
        }

        System.out.println(total+"->>"+page);
        for (int i = 0; i < page; i++) {
            int pageIndex = i;
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                System.out.println("Running in thread: " + Thread.currentThread().getName());
                Sheet sheet = workbook.getSheetAt(pageIndex);
                writeHeader(sheet);
                write(hoaDonService.traCuuHoaDon(dto, PageRequest.of(pageIndex,10000)),sheet);
                System.out.println("sheet "+pageIndex+" xong");
            }, taskExecutor);
            futures.add(future);
        }
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allOf.join();  // Sẽ chờ cho đến khi tất cả hoàn thành
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=hoadon.xlsx");
            workbook.write(outputStream);
            outputStream.flush();
        } finally {
            workbook.close();
            System.out.println("xong");
            System.out.println(LocalDateTime.now());
        }
    }



    public void readExcelFromInputStream(InputStream inputStream) throws IOException {

        try (Workbook workbook = StreamingReader.builder()
                .rowCacheSize(100)     // Số hàng giữ lại trong bộ nhớ
                .bufferSize(4096)      // Bộ đệm đọc file
                .open(inputStream)) {   // inputStream là InputStream của file Excel
            for (Sheet sheet : workbook) {
                System.out.println("Sheet: " + sheet.getSheetName());

                for (Row row : sheet) {
                    if (row.getRowNum() == 0) {
                        if (row.getPhysicalNumberOfCells() != 19) throw new RuntimeException("fileKoDungDinhDang");
                    } else{
                        if (row.getCell(18)!=null && row.getCell(18).getNumericCellValue() == 2) {
                            String id = row.getCell(0).getStringCellValue();
                            String lydo = row.getCell(17)!=null?row.getCell(17).getStringCellValue():null;
                            System.out.println("id:"+id+
                                    "ly do: "+lydo);
                        }
                    }
                }
            }



            System.out.println("doc xong");
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
}



}
