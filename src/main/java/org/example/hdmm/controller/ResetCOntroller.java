package org.example.hdmm.controller;

import org.example.hdmm.service.reset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reset")
public class ResetCOntroller {
    @Autowired
    reset r;
    @RequestMapping("")
    String resetAll(){
        r.resetAll();
        return "reset all";
    }
}
