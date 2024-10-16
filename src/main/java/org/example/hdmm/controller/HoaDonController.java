package org.example.hdmm.controller;

import org.example.hdmm.dto.FindHoaDonDTO;
import org.example.hdmm.models.HoaDon;
import org.example.hdmm.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/hdon")
public class HoaDonController {
    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("/cqt/{cqt}/sd/{startDate}/ed/{endDate}")
    public List<Object[]> countData(@PathVariable("cqt") String cqt,
                                   @PathVariable("startDate")  Date startDate,
                                   @PathVariable("endDate")  Date endDate) {
        return hoaDonService.countData(cqt, startDate, endDate);
    }

    @PostMapping("/tracuu")
    public ResponseEntity<List<HoaDon>> findHoaDonDTOS(@RequestBody FindHoaDonDTO dto,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page,size);
        List<HoaDon> list= hoaDonService.traCuuHoaDon(dto,pageable);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @PostMapping("/counttracuu")
    public ResponseEntity<Integer> counttracuu(@RequestBody FindHoaDonDTO dto) {
        Integer total= hoaDonService.countTraCuuHoaDon(dto);
        return ResponseEntity.status(HttpStatus.OK).body(total);
    }









}
