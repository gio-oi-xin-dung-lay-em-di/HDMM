package org.example.hdmm.service;

import org.example.hdmm.dto.GiaiThuongDTO;
import org.example.hdmm.dto.UpdateGiaiThuongDTO;
import org.example.hdmm.models.GiaiThuong;
import org.example.hdmm.models.HoaDon;
import org.example.hdmm.models.KyQuayThuong;
import org.example.hdmm.repository.GiaiThuongReppository;
import org.example.hdmm.repository.KyQuayThuongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Random;

@Service
public class GiaiThuongService {
    @Autowired
    private GiaiThuongReppository giaiThuongReppository;
    @Autowired
    private KyQuayThuongRepository kyQuayThuongRepository;
    @Autowired
    private KetQuaService ketQuaService;
    @Autowired
    private HoaDonService hoaDonService;

    public GiaiThuong create(GiaiThuongDTO dto,Long kyQuayThuongId){
        // neu giai thuong da ton tai
        if(giaiThuongReppository.listByGiaiAndKy(kyQuayThuongId,dto.getGiaiThuong()).size()>0)  throw new RuntimeException("existed");
        if(dto.getGiaTri()==null||dto.getGiaTri()<1000000) throw new RuntimeException("giatriinvalid");
        if(dto.getSoLuong()==null||dto.getSoLuong()<1) throw new RuntimeException("soluongInvalid");
        if(((dto.getSoGiaiCN()!=null&&dto.getSoGiaiDN()!=null)&&(dto.getSoGiaiCN()+dto.getSoGiaiDN()>dto.getSoLuong()))
        ) throw new RuntimeException("giaiCNvaDNinvalid");
        if(dto.getSoGiaiCN()!=null&& dto.getSoGiaiCN()>dto.getSoLuong()) throw new RuntimeException("giaiCNvaDNinvalid");
        if(dto.getSoGiaiDN()!=null&& dto.getSoGiaiDN()>dto.getSoLuong()) throw new RuntimeException("giaiCNvaDNinvalid");


        GiaiThuong giaiThuong = new GiaiThuong();

        Random random = new Random();
        giaiThuong.setId(random.nextLong());

        giaiThuong.setGiaiThuong(dto.getGiaiThuong());
        giaiThuong.setGiaTri(dto.getGiaTri());
        giaiThuong.setSoLuong(dto.getSoLuong());
        if(dto.getSoGiaiDN()!=null) giaiThuong.setSoGiaiDN(dto.getSoGiaiDN());
        if(dto.getSoGiaiCN()!=null) giaiThuong.setSoGiaiCN(dto.getSoGiaiCN());
        KyQuayThuong kyQuayThuong = kyQuayThuongRepository.findById(kyQuayThuongId).orElseThrow(()-> new RuntimeException("Ky quay thuong khong ton tai"));
        giaiThuong.setKyQuayThuong(kyQuayThuong);
        return giaiThuongReppository.save(giaiThuong);
    }

    public GiaiThuong update(UpdateGiaiThuongDTO dto, Long giaithuongId){

        if(dto.getGiaTri()==null||dto.getGiaTri()<1000000) throw new RuntimeException("giatriinvalid");
        if(dto.getSoLuong()==null||dto.getSoLuong()<1) throw new RuntimeException("soluongInvalid");
        if(dto.getSoGiaiCN()!=null && dto.getSoGiaiDN()!=null && (dto.getSoGiaiCN()+dto.getSoGiaiDN()!=dto.getSoLuong())) throw new RuntimeException("giaiCNvaDNinvalid");

        GiaiThuong giaiThuong = giaiThuongReppository.findById(giaithuongId).orElseThrow(()->new RuntimeException("Khong tim thay giai thuong"));

        giaiThuong.setGiaTri(dto.getGiaTri());
        giaiThuong.setSoLuong(dto.getSoLuong());
        giaiThuong.setSoGiaiCN(dto.getSoGiaiCN());
        giaiThuong.setSoGiaiDN(dto.getSoGiaiDN());
        return giaiThuongReppository.save(giaiThuong);

    }



    public GiaiThuong finById(Long id){
        return giaiThuongReppository.findById(id).orElseThrow(()-> new RuntimeException("Khong tim thay giai thuong"));
    }
    public List<GiaiThuong> findByKyQuayThuong(KyQuayThuong kqtId){
        return giaiThuongReppository.findByKyQuayThuong(kqtId);
    }
    public void delete(Long giaiThuongID){
        giaiThuongReppository.deleteById(giaiThuongID);
    }



}
