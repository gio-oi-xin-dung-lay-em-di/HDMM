package org.example.hdmm.service;

import org.example.hdmm.repository.GiamSatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiamSatService {
    @Autowired
    private GiamSatRepository giamSatRepository;

}
