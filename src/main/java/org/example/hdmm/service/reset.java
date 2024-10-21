package org.example.hdmm.service;

import org.example.hdmm.repository.GiaiThuongReppository;
import org.example.hdmm.repository.GiamSatRepository;
import org.example.hdmm.repository.KetQuaRepository;
import org.example.hdmm.repository.KyQuayThuongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class reset {
    @Autowired
    KyQuayThuongRepository kyQuayThuongRepository;
    @Autowired
    KetQuaRepository ketQuaRepository;
    @Autowired
    GiamSatRepository giamSatRepository;
    @Autowired
    GiaiThuongReppository giaiThuongReppository;

    public void resetAll() {
        ketQuaRepository.deleteAll();
        giamSatRepository.deleteAll();
        giaiThuongReppository.deleteAll();
        kyQuayThuongRepository.deleteAll();
    }
}
