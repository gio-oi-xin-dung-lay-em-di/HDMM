package org.example.hdmm.service;

import org.example.hdmm.dto.KyQuayThuongDTO;
import org.example.hdmm.dto.UpdateKQTDTO;
import org.example.hdmm.models.CoQuanThue;
import org.example.hdmm.models.KyQuayThuong;
import org.example.hdmm.repository.CoQuanThueRepository;
import org.example.hdmm.repository.KyQuayThuongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KyQuayThuongService {
    @Autowired
    private KyQuayThuongRepository kyQuayThuongRepository;
    @Autowired
    private CoQuanThueRepository coQuanThueRepository;


    public List<KyQuayThuong> getAllKyQuayThuongByCQT(Integer cqt) {
        return kyQuayThuongRepository.findAllByCQT(cqt);
    }

    // create
    public KyQuayThuong create(KyQuayThuongDTO dto) {
        if(kyQuayThuongRepository.existsByMaKyAndCoQuanThue_Id(dto.getMaKy(), dto.getCoQuanThueId())) throw new RuntimeException("Ma ky da ton tai");

        if(kyQuayThuongRepository.existsByDate(dto.getTuNgay(),dto.getCoQuanThueId())||kyQuayThuongRepository.existsByDate(dto.getDenNgay(), dto.getCoQuanThueId())||dto.getTuNgay().compareTo(dto.getDenNgay()) > 0) throw new RuntimeException("Ngay khong hop le");

        KyQuayThuong kqt = new KyQuayThuong();
        kqt.setMaKy(dto.getMaKy());
        kqt.setTenKy(dto.getTenKy());
        kqt.setTuNgay(dto.getTuNgay());
        kqt.setDenNgay(dto.getDenNgay());

        CoQuanThue cqt =coQuanThueRepository.findById(dto.getCoQuanThueId())
                .orElseThrow(() -> new RuntimeException("Ko tim thay cqt"));

        kqt.setCoQuanThue(cqt);
        return kyQuayThuongRepository.save(kqt);

    }
 //update
    public KyQuayThuong update(UpdateKQTDTO dto){

        Long id = dto.getId().longValue();
        KyQuayThuong kqt = kyQuayThuongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ko tim thay Ky Quay Thuong"));
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
