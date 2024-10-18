package org.example.hdmm.service;

import org.example.hdmm.models.GiaiThuong;
import org.example.hdmm.models.HoaDon;
import org.example.hdmm.models.KetQua;
import org.example.hdmm.repository.GiaiThuongReppository;
import org.example.hdmm.repository.HoaDonRepository;
import org.example.hdmm.repository.KetQuaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class KetQuaService {
    @Autowired
    private KetQuaRepository ketQuaRepository;
    public KetQua create(GiaiThuong giai,HoaDon hoaDon){
        KetQua kq = new KetQua();
        kq.setId(new Random().nextLong());
        kq.setGiaiThuong(giai);
        kq.setHoaDon(hoaDon);
        ketQuaRepository.save(kq);
        ketQuaRepository.flush();
        return kq;
    }
    public List<KetQua> findAll(){
        System.out.println(" day la contrll");
        return ketQuaRepository.findAll();
    }
    public void deleteAll(){
        ketQuaRepository.deleteAll();
    }

}
