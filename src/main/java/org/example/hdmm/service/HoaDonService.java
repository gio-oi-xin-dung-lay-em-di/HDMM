package org.example.hdmm.service;

import org.example.hdmm.dto.FindHoaDonDTO;
import org.example.hdmm.models.HoaDon;
import org.example.hdmm.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Service
public class HoaDonService{
    @Autowired
    private HoaDonRepository hoaDonRepository;
    public List<HoaDon> find10HoaDon(Pageable pageable){
        return hoaDonRepository.find10HoaDon( pageable);
    };


    public void setAllKy(Date startDate, Date endDate , Integer ky){
        hoaDonRepository.setAllKy(startDate,endDate,ky);
    }


//    public void updateKy(Date startDate, Date endDate , Integer ky){
//        hoaDonRepository.updateAllKy(startDate,endDate,ky);
//    }

    public List<Object[]> countData(String cqt , Date startDate , Date endDate){
        return hoaDonRepository.countData(cqt,startDate,endDate);
    }
    public List<HoaDon> traCuuHoaDon(FindHoaDonDTO dto,Pageable pageable){
        return hoaDonRepository.traCuuHoaDon(dto.getKy(),dto.getTuNgay(),dto.getDenNgay(),dto.getTrangThaiThamGia(),dto.getNmMst(),dto.getNmTen(),dto.getTtHdon(),dto.getLoaiNnt(),dto.getLoaiHd(),dto.getKyHieu(),dto.getSo(),dto.getNbMst(),dto.getNbTen(),dto.getCqt(),pageable);
    }
    public Integer countTraCuuHoaDon(FindHoaDonDTO dto){
        return hoaDonRepository.countTraCuuHoaDon(dto.getKy(),dto.getTuNgay(),dto.getDenNgay(),dto.getTrangThaiThamGia(),dto.getNmMst(),dto.getNmTen(),dto.getTtHdon(),dto.getLoaiNnt(),dto.getLoaiHd(),dto.getKyHieu(),dto.getSo(),dto.getNbMst(),dto.getNbTen(),dto.getCqt());
    }

    public HoaDon hoadonmayman (String cqt, Date startDate,Date endDate,Integer loaiNnt,Integer number){
        Pageable page = PageRequest.of(number,1);
        return hoaDonRepository.quayThuong(cqt,startDate,endDate,loaiNnt,page).get(0);
    }


}
