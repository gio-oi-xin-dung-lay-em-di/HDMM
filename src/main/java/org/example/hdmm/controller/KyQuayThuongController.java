package org.example.hdmm.controller;

import com.mysql.cj.x.protobuf.Mysqlx;
import org.example.hdmm.dto.KyQuayThuongDTO;
import org.example.hdmm.dto.UpdateKQTDTO;
import org.example.hdmm.models.KyQuayThuong;
import org.example.hdmm.service.KyQuayThuongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kiquaythuong")
public class KyQuayThuongController {


    private final KyQuayThuongService kyQuayThuongService;

    public KyQuayThuongController(KyQuayThuongService kyQuayThuongService) {
        this.kyQuayThuongService = kyQuayThuongService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<KyQuayThuong> findById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(kyQuayThuongService.getKyQuayThuongById(id));
    }

    @GetMapping("/cqt/{cqtId}")
    public ResponseEntity<List<KyQuayThuong>> findAll(@PathVariable Integer cqtId) {
        return ResponseEntity.status(HttpStatus.OK).body(kyQuayThuongService.getAllKyQuayThuongByCQT(cqtId));
    }
    @PostMapping
    public ResponseEntity<KyQuayThuong> createKyQuayThuong(@RequestBody KyQuayThuongDTO dto) {
        KyQuayThuong kqt = kyQuayThuongService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(kqt);
    }
    @PutMapping("/{id}")
    public ResponseEntity<KyQuayThuong> updateKyQuayThuong(@RequestBody UpdateKQTDTO dto , @PathVariable("id") Long id) {
        KyQuayThuong kqt = kyQuayThuongService.update(id , dto);
        return ResponseEntity.status(HttpStatus.OK).body(kqt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteKyQuayThuong( @PathVariable("id") Long id) {
        kyQuayThuongService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("xoa thanh cong");
    }




}
