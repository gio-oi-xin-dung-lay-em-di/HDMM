package org.example.hdmm.controller;

import org.example.hdmm.dto.GiaiThuongDTO;
import org.example.hdmm.dto.UpdateGiaiThuongDTO;
import org.example.hdmm.models.GiaiThuong;
import org.example.hdmm.service.GiaiThuongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/giaithuong")
public class GiaiThuongController {
    @Autowired
    private GiaiThuongService giaiThuongService;
    @GetMapping("/{id}")
    public ResponseEntity<GiaiThuong> getById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(giaiThuongService.finById(id));
    }
    @GetMapping("/ky/{id}")
    public ResponseEntity<List<GiaiThuong>> getByKy(@PathVariable("id") Long id){



        return ResponseEntity.status(HttpStatus.OK).body(giaiThuongService.findByKyQuayThuong(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGiaiThuong(@PathVariable("id") Long id){
        giaiThuongService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Xoa thanh cong");
    }
    @PostMapping("/{ky}")
    public ResponseEntity<GiaiThuong> createGiaiThuong(@PathVariable("ky") Long ky, @RequestBody GiaiThuongDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(giaiThuongService.create(dto,ky));
    }
    @PutMapping("/{ky}")
    public ResponseEntity<GiaiThuong> updateGiaiThuong(@PathVariable("ky") Long idGiai, @RequestBody UpdateGiaiThuongDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(giaiThuongService.update(dto,idGiai));
    }





}
