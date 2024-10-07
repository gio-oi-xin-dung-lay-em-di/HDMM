package org.example.hdmm.service;

import org.example.hdmm.dto.KyQuayThuongDTO;
import org.example.hdmm.dto.UpdateKQTDTO;
import org.example.hdmm.models.CoQuanThue;
import org.example.hdmm.models.KyQuayThuong;
import org.example.hdmm.repository.CoQuanThueRepository;
import org.example.hdmm.repository.KyQuayThuongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KyQuayThuongService {
    @Autowired
    private KyQuayThuongRepository kyQuayThuongRepository;
    @Autowired
    private CoQuanThueRepository coQuanThueRepository;

    public KyQuayThuong  getKyQuayThuongById(Integer id){
        return kyQuayThuongRepository.findById(Long.valueOf(id)).orElseThrow(()->new RuntimeException("Khong tim thay ky quay thuong"));
    }

    public List<KyQuayThuong> getAllKyQuayThuongByCQT(String cqt, Pageable pageable) {
        return kyQuayThuongRepository.findAllByCQT(cqt,pageable);
    }

    // create
    public KyQuayThuong create(KyQuayThuongDTO dto) {
        if(dto.getTuNgay().compareTo(dto.getDenNgay())>0) throw new RuntimeException("ngay bat dau phai nho hon ngay ket thuc");
        if(kyQuayThuongRepository.existsByMaKyAndCoQuanThue_Id(dto.getMaKy(), dto.getCoQuanThueId())) throw new RuntimeException("Ma ky da ton tai");
        if(kyQuayThuongRepository.existsByDate(dto.getTuNgay(), dto.getCoQuanThueId())) throw new RuntimeException("Ngay bat dau khong hop le");
        if(kyQuayThuongRepository.existsByDate(dto.getDenNgay(), dto.getCoQuanThueId())) throw new RuntimeException("Ngay ket thuc khong hop le");
        if(dto.getTuNgay().compareTo(dto.getDenNgay())>0) throw new RuntimeException("ngay bat dau phai nho hon ngay ket thuc");

        KyQuayThuong kqt = new KyQuayThuong();
        kqt.setMaKy(dto.getMaKy());
        kqt.setTenKy(dto.getTenKy());
        kqt.setTuNgay(dto.getTuNgay());
        kqt.setDenNgay(dto.getDenNgay());
        kqt.setStatus(1);
        CoQuanThue cqt =coQuanThueRepository.findById(dto.getCoQuanThueId())
                .orElseThrow(() -> new RuntimeException("Ko tim thay cqt"));

        kqt.setCoQuanThue(cqt);
        return kyQuayThuongRepository.save(kqt);

    }
 //update
    public KyQuayThuong update(Long id , UpdateKQTDTO dto){


        KyQuayThuong kqt = kyQuayThuongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ko tim thay Ky Quay Thuong"));

        if(kqt.getTuNgay() == dto.getTuNgay()||kqt.getDenNgay()== dto.getDenNgay()) throw new RuntimeException("Tu ngay hoac den ngay khong duoc trung");

        if(dto.getTuNgay().compareTo(kqt.getDenNgay())>0) throw new RuntimeException("ngay bat dau phai nho hon ngay ket thuc");
        if(dto.getTuNgay().compareTo(kqt.getDenNgay())>0) throw new RuntimeException("ngay bat dau phai nho hon ngay ket thuc");




        if(kyQuayThuongRepository.existsByDate(dto.getTuNgay(), kqt.getCoQuanThue().getCqt())) throw new RuntimeException("Ngay bat dau khong hop le");
        if(kyQuayThuongRepository.existsByDate(dto.getDenNgay(), kqt.getCoQuanThue().getCqt())) throw new RuntimeException("Ngay ket thuc khong hop le");
        if(dto.getTenKy().isEmpty()||dto.getTenKy().length()>100) throw new RuntimeException("Ten ky khong hop le");
        kqt.setTenKy(dto.getTenKy());
        kqt.setTuNgay(dto.getTuNgay());
        kqt.setDenNgay(dto.getDenNgay());
        return kyQuayThuongRepository.save(kqt);

    }

    // Delete
    public void delete(Long id) {
        kyQuayThuongRepository.deleteById(id);
    }





}
