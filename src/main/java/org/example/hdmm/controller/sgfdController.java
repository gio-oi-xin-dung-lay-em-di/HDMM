package org.example.hdmm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class sgfdController {
    List<Integer> list = new ArrayList<Integer>();
    @GetMapping("setList")
    public List<Integer> getList() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        return list;
    }
    @GetMapping("getList")
    public List<Integer> getList2() {
        return list;
    }



}
