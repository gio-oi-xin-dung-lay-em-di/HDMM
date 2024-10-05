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
    @GetMapping("/cqt/{cqtId}")
    public ResponseEntity<List<KyQuayThuong>> findAll(@PathVariable Integer cqtId) {
        return ResponseEntity.status(HttpStatus.OK).body(kyQuayThuongService.getAllKyQuayThuongByCQT(cqtId));
    }
    @PostMapping
    public ResponseEntity<KyQuayThuong> createKyQuayThuong(@RequestBody KyQuayThuongDTO dto) {
        KyQuayThuong kqt = kyQuayThuongService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(kqt);
    }
    @PutMapping
    public ResponseEntity<KyQuayThuong> updateKyQuayThuong(@RequestBody UpdateKQTDTO dto) {
        KyQuayThuong kqt = kyQuayThuongService.update(dto);
        return ResponseEntity.status(HttpStatus.OK).body(kqt);
    }



}
