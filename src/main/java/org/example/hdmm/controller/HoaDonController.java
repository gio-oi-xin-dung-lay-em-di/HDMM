package org.example.hdmm.controller;

import org.example.hdmm.models.HoaDon;
import org.example.hdmm.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hdon")
public class HoaDonController {
    @Autowired
    private HoaDonService hoaDonService;
    @GetMapping("")
    public ResponseEntity<List<HoaDon>> getTop10fsv( @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {




        Pageable pageable = PageRequest.of(page, size);
        List<HoaDon> hoaDonList = hoaDonService.find10HoaDon(pageable);
        return ResponseEntity.ok(hoaDonList);
    }


}
