package org.example.hdmm.service;

import org.example.hdmm.models.GiaiThuong;
import org.example.hdmm.models.HoaDon;
import org.example.hdmm.models.KetQua;
import org.example.hdmm.repository.GiaiThuongReppository;
import org.example.hdmm.repository.HoaDonRepository;
import org.example.hdmm.repository.KetQuaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class KetQuaService {
    @Autowired
    private KetQuaRepository ketQuaRepository;
    @Autowired
    private GiaiThuongReppository giaiThuongReppository;
    @Autowired
    private HoaDonRepository hoaDonRepository;
    public KetQua create(GiaiThuong giai,HoaDon hoaDon){
        KetQua kq = new KetQua();
        kq.setId(new Random().nextLong());
        kq.setGiaiThuong(giai);
        kq.setHoaDon(hoaDon);
        return ketQuaRepository.save(kq);
    }
}
