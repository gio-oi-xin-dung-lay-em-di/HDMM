package org.example.hdmm.service;

import org.example.hdmm.dto.GiamSatDTO;
import org.example.hdmm.dto.UpdateGiamSatDTO;
import org.example.hdmm.models.GiamSat;
import org.example.hdmm.models.KyQuayThuong;
import org.example.hdmm.repository.GiamSatRepository;
import org.example.hdmm.repository.KyQuayThuongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiamSatService {
    @Autowired
    private GiamSatRepository giamSatRepository;
    @Autowired
    KyQuayThuongRepository kyQuayThuongRepository;
    public GiamSat create(GiamSatDTO dto,Long ky){
        KyQuayThuong kqt = kyQuayThuongRepository.findById(ky).orElseThrow(()-> new RuntimeException("Khong tim thay ky quay thuong"));
        if(giamSatRepository.getGiamSatsByKyQuayThuong_IdAndSTT(ky,dto.getStt()).size()>0) throw new RuntimeException("STT da ton tai");
        GiamSat gs = new GiamSat();
        gs.setSTT(dto.getStt());
        gs.setGioiTinh(dto.getGioiTinh());
        gs.setHoTen(dto.getHoTen());
        gs.setDonVi(dto.getDonVi());
        gs.setKyQuayThuong(kqt);
        gs.setChucDanh(dto.getChucDanh());
        return giamSatRepository.save(gs);
    }

    public GiamSat update(UpdateGiamSatDTO dto, Long id){


        GiamSat gs = giamSatRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("nguoi giam sat khong ton tai"));
        gs.setGioiTinh(dto.getGioiTinh());
        gs.setHoTen(dto.getHoTen());
        gs.setDonVi(dto.getDonVi());
        gs.setChucDanh(dto.getChucDanh());
        return giamSatRepository.save(gs);

    }
    public void delete(Long id){
        giamSatRepository.deleteById(id);
    }
    public List<GiamSat> getByKy(Long ky){
        return giamSatRepository.getGiamSatsByKyQuayThuong_Id(ky);
    }

    public GiamSat getById(Long Id){
        return giamSatRepository.findById(Id).orElseThrow(()-> new RuntimeException("Khong tim thay giam sat"));
    }


}
