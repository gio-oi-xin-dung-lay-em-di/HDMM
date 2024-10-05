package org.example.hdmm.service;

import org.example.hdmm.repository.GiaiThuongReppository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiaiThuongService {
    @Autowired
    private GiaiThuongReppository giaiThuongReppository;

}
