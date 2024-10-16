package org.example.hdmm.controller;

import org.example.hdmm.models.CoQuanThue;
import org.example.hdmm.models.HoaDon;
import org.example.hdmm.service.CoQuanTHueService;
import org.example.hdmm.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/coquanthue")
public class CoQuanThueController {
    @Autowired
    private CoQuanTHueService coQuanTHueService;

    @GetMapping("")
    @CrossOrigin
    public ResponseEntity<List<CoQuanThue>> getTop10fsv() {
        List<CoQuanThue> listCQT = coQuanTHueService.findAll();
        return ResponseEntity.ok(listCQT);
    }


}