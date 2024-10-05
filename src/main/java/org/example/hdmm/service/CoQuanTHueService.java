package org.example.hdmm.service;

import org.example.hdmm.models.CoQuanThue;
import org.example.hdmm.models.HoaDon;
import org.example.hdmm.repository.CoQuanThueRepository;
import org.example.hdmm.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoQuanTHueService {
    @Autowired
    private CoQuanThueRepository coQuanThueRepository;


    public List<CoQuanThue> findAll(){
        return coQuanThueRepository.findAll();
    };
}
