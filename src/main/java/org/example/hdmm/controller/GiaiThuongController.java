package org.example.hdmm.controller;

import org.example.hdmm.dto.GiaiThuongDTO;
import org.example.hdmm.dto.UpdateGiaiThuongDTO;
import org.example.hdmm.models.GiaiThuong;
import org.example.hdmm.models.KyQuayThuong;
import org.example.hdmm.repository.KyQuayThuongRepository;
import org.example.hdmm.service.GiaiThuongService;
import org.example.hdmm.service.KyQuayThuongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/giaithuong")
public class GiaiThuongController {
    @Autowired

    private GiaiThuongService giaiThuongService;
    @Autowired
    private KyQuayThuongService kyQuayThuongService;
    @Autowired
    private KyQuayThuongRepository kyQuayThuongRepository;

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<GiaiThuong> getById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(giaiThuongService.finById(id));
    }
    @GetMapping("/ky/{id}")
    @CrossOrigin
    public ResponseEntity<List<GiaiThuong>> getByKy(@PathVariable("id") Long id){
        KyQuayThuong kqt = kyQuayThuongRepository.findById(id).orElseThrow(()->new RuntimeException("kqtNotFound"));


        return ResponseEntity.status(HttpStatus.OK).body(giaiThuongService.findByKyQuayThuong(kqt));
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<String> deleteGiaiThuong(@PathVariable("id") Long id){
        giaiThuongService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Xoa thanh cong");
    }
    @PostMapping("/ky/{ky}")
    @CrossOrigin
    public ResponseEntity<GiaiThuong> createGiaiThuong(@PathVariable("ky") Long ky, @RequestBody GiaiThuongDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(giaiThuongService.create(dto,ky));
    }
    @PutMapping("/id/{idGiai}")
    @CrossOrigin
    public ResponseEntity<GiaiThuong> updateGiaiThuong(@PathVariable("idGiai") Long idGiai, @RequestBody UpdateGiaiThuongDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(giaiThuongService.update(dto,idGiai));
    }







}
