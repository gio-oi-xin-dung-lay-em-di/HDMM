package org.example.hdmm.service;

import org.example.hdmm.repository.KetQuaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KetQuaService {
    @Autowired
    private KetQuaRepository ketQuaRepository;

}
