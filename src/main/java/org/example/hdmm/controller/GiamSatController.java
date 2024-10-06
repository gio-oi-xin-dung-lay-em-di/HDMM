package org.example.hdmm.controller;

import org.example.hdmm.dto.GiamSatDTO;
import org.example.hdmm.dto.UpdateGiamSatDTO;
import org.example.hdmm.models.GiamSat;
import org.example.hdmm.service.GiamSatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/giamsat")
public class GiamSatController {

    @Autowired
    private GiamSatService giamSatService;

    @GetMapping("/{id}")
    public ResponseEntity<GiamSat> getById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(giamSatService.getById(id));
    }

    @GetMapping("/ky/{id}")
    public ResponseEntity<List<GiamSat>> getByKy(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(giamSatService.getByKy(id));
    }

    @PostMapping("/ky/{id}")
    public ResponseEntity<GiamSat> createGiamSat(@PathVariable("id") Long ky, @RequestBody GiamSatDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(giamSatService.create(dto,ky));
    }
    @PutMapping("/{id}")
    public ResponseEntity<GiamSat> updaGiamSat(@PathVariable("id") Long id, @RequestBody UpdateGiamSatDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(giamSatService.update(dto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGiamSat(@PathVariable("id") Long id){
        giamSatService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("xoa thanh cong giam sat "+id);
    }


}
