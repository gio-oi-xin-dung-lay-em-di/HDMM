package org.example.hdmm.service;

import org.example.hdmm.models.HoaDon;
import org.example.hdmm.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonService{
    @Autowired
    private HoaDonRepository hoaDonRepository;
    public List<HoaDon> find10HoaDon(Pageable pageable){
        return hoaDonRepository.find10HoaDon( pageable);
    };
}
