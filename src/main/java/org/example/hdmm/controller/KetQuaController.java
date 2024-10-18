package org.example.hdmm.controller;

import org.example.hdmm.models.KetQua;
import org.example.hdmm.service.KetQuaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ketqua")
public class KetQuaController {
    @Autowired
    private KetQuaService ketQuaService;


    @GetMapping()
    public List<KetQua> findAll(){
        return ketQuaService.findAll();
    }
    @DeleteMapping()
    public String deleteAll(){
         ketQuaService.deleteAll();
         return "Xoa Ok";
    }
}
