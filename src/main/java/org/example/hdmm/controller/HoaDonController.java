package org.example.hdmm.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.hdmm.dto.FindHoaDonDTO;
import org.example.hdmm.dto.LoaiBoHdonDTO;
import org.example.hdmm.models.HoaDon;
import org.example.hdmm.service.ExportHdon;
import org.example.hdmm.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/hdon")
public class HoaDonController {
    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("/cqt/{cqt}/sd/{startDate}/ed/{endDate}")
    public List<Object[]> countData(@PathVariable("cqt") String cqt,
                                   @PathVariable("startDate") Date startDate,
                                   @PathVariable("endDate")  Date endDate) {
        return hoaDonService.countData(cqt, startDate, endDate);
    }

    @PostMapping("/tracuu")
    public ResponseEntity<List<HoaDon>> findHoaDonDTOS(@RequestBody FindHoaDonDTO dto,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page,size);
        List<HoaDon> list= hoaDonService.traCuuHoaDon(dto,pageable);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @PostMapping("/counttracuu")
    public ResponseEntity<Integer> counttracuu(@RequestBody FindHoaDonDTO dto) {
        Integer total= hoaDonService.countTraCuuHoaDon(dto);
        return ResponseEntity.status(HttpStatus.OK).body(total);
    }
    @PostMapping("/loaibo")
    public String loaiBoHdon(@RequestBody LoaiBoHdonDTO dto) {
        hoaDonService.loaiBo(dto.getData(),dto.getListhdon());
        return "daLoaiBoHdon";
    }

    @PostMapping("/chapnhan")
    public String chapNhan(@RequestBody LoaiBoHdonDTO dto) {
        hoaDonService.chapNhan(dto.getData(),dto.getListhdon());
        return "daChapNhanHdon";
    }



    @GetMapping("/updateStt/{cqt}/{startDate}/{endDate}/{loaiNnt}")
    public void countData(@PathVariable("cqt") String cqt,
                          @PathVariable("startDate")Date startDate,
                          @PathVariable("endDate")Date endDate,
                          @PathVariable("loaiNnt") Integer loaiNnt) {
         hoaDonService.updateStt(cqt,loaiNnt, startDate, endDate);
    }

    @PutMapping("/resetStt")
    public void reset(){
        hoaDonService.resetStt();
    }
    @Autowired
    private ExportHdon exportHdon;
    @PostMapping("/export-to-excel")
    public void exportIntoExcelFile(HttpServletResponse response, @RequestBody FindHoaDonDTO dto) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=hoadon.xlsx";
        response.setHeader(headerKey, headerValue);
        response.setHeader(headerKey, headerValue);

        exportHdon.generateExcelFile(response,dto);
    }


    @PostMapping("/import")
    public String mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {
        String fileName = reapExcelDataFile.getOriginalFilename();
        boolean validFile = fileName != null && (fileName.toLowerCase().endsWith(".xls") || fileName.toLowerCase().endsWith(".xlsx"));
        if (!validFile) {
            throw new RuntimeException("fileTypeInvalid");
        } else {
            System.out.println("file dung");
        }
        try(InputStream is = reapExcelDataFile.getInputStream()){
            exportHdon.readExcelFromInputStream(is);
        }

        return "success";
    }




}
