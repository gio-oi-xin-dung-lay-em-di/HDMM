package org.example.hdmm.controller;
import org.example.hdmm.dto.KyQuayThuongDTO;
import org.example.hdmm.dto.UpdateKQTDTO;
import org.example.hdmm.models.GiaiThuong;
import org.example.hdmm.models.KyQuayThuong;
import org.example.hdmm.service.HoaDonService;
import org.example.hdmm.service.KyQuayThuongService;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/kiquaythuong")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class KyQuayThuongController {


    private final KyQuayThuongService kyQuayThuongService;
    private final HoaDonService hoaDonService;

    public KyQuayThuongController(KyQuayThuongService kyQuayThuongService, HoaDonService hoaDonService) {
        this.kyQuayThuongService = kyQuayThuongService;
        this.hoaDonService = hoaDonService;
    }

    @GetMapping("/{id}")

    public ResponseEntity<KyQuayThuong> findById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(kyQuayThuongService.getKyQuayThuongById(id));
    }


    @GetMapping("/total/cqt/{cqt}")

    public ResponseEntity<Integer> totalRecord(@PathVariable("cqt") String cqt) {
        return ResponseEntity.status(HttpStatus.OK).body(kyQuayThuongService.countTotal(cqt));
    }

    @GetMapping("/cqt/{cqtId}")
    public ResponseEntity<List<KyQuayThuong>> findAll(@PathVariable String cqtId,@RequestParam(name = "size", defaultValue = "10") int size,@RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(kyQuayThuongService.getAllKyQuayThuongByCQT(cqtId,pageable));
    }



    @GetMapping("/quaythuong/{id}")
    public ResponseEntity<List<GiaiThuong>> quayThuong(@PathVariable(name = "id") Long id) {
        List<GiaiThuong> listGiai = kyQuayThuongService.quayThuong(id);
        return ResponseEntity.status(HttpStatus.OK).body(listGiai);
    }
    @PostMapping("/cqt/{cqt}")

    public ResponseEntity<KyQuayThuong> createKyQuayThuong(@RequestBody KyQuayThuongDTO dto ,@PathVariable("cqt") String cqt) {

        KyQuayThuong kqt = kyQuayThuongService.create(dto,cqt);
        return ResponseEntity.status(HttpStatus.CREATED).body(kqt);
    }
    @PutMapping("/{id}")

    public ResponseEntity<KyQuayThuong> updateKyQuayThuong(@RequestBody UpdateKQTDTO dto , @PathVariable("id") Long id) {
        KyQuayThuong kqt = kyQuayThuongService.update(id , dto);
        return ResponseEntity.status(HttpStatus.OK).body(kqt);
    }
    @PutMapping("/xuli/{id}")

    public ResponseEntity<KyQuayThuong> processKyQuayThuong( @PathVariable("id") Long id) {
        KyQuayThuong kqt = kyQuayThuongService.xuli(id);
        return ResponseEntity.status(HttpStatus.OK).body(kqt);
    }
    @PutMapping("/id/{id}/status/{status}")

    public ResponseEntity<KyQuayThuong> updateStatus( @PathVariable("id") Long id,@PathVariable("status") Integer status) {
        KyQuayThuong kqt = kyQuayThuongService.updateStatus(id,status);
        return ResponseEntity.status(HttpStatus.OK).body(kqt);
    }


    @DeleteMapping("/{id}")

    public ResponseEntity<String> deleteKyQuayThuong( @PathVariable("id") Long id) {
        kyQuayThuongService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("xoa thanh cong");
    }


    @GetMapping("/{cqt}/{tungay}/{denngay}")

    public ResponseEntity<List<Object[]>> count(@PathVariable("cqt") String cqt, @PathVariable("tungay") Date tungay, @PathVariable("denngay") Date denngay) {

        return ResponseEntity.status(HttpStatus.OK).body(hoaDonService.countData(cqt,tungay,denngay));
    }
    @GetMapping("/quaythuong2/{kqtid}")

    public ResponseEntity<List<GiaiThuong>> quaythuong(@PathVariable("kqtid") Long kqtid) {
        return ResponseEntity.status(HttpStatus.OK).body(kyQuayThuongService.quayThuong(kqtid));
    }





}
