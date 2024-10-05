package org.example.hdmm.controller;

import org.example.hdmm.models.CoQuanThue;
import org.example.hdmm.models.HoaDon;
import org.example.hdmm.service.CoQuanTHueService;
import org.example.hdmm.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hdon2")
public class CoQuanThueController {
    @Autowired
    private CoQuanTHueService coQuanTHueService;
    @GetMapping("")
    public ResponseEntity<List<HoaDon>> getTop10fsv() {
        List<HoaDon> listCQT = coQuanTHueService.findAll().get(0).getHoaDonList();
        return ResponseEntity.ok(listCQT);
    }


}