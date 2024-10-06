package org.example.hdmm.service;

import org.example.hdmm.dto.GiaiThuongDTO;
import org.example.hdmm.dto.UpdateGiaiThuongDTO;
import org.example.hdmm.models.GiaiThuong;
import org.example.hdmm.models.KyQuayThuong;
import org.example.hdmm.repository.GiaiThuongReppository;
import org.example.hdmm.repository.KyQuayThuongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiaiThuongService {
    @Autowired
    private GiaiThuongReppository giaiThuongReppository;
    @Autowired
    private KyQuayThuongRepository kyQuayThuongRepository;

    public GiaiThuong create(GiaiThuongDTO dto,Long kyQuayThuongId){
        // neu giai thuong da ton tai
        if(giaiThuongReppository.listByGiaiAndKy(kyQuayThuongId,dto.getGiaiThuong()).size()>0)  throw new RuntimeException("Giai "+dto.getGiaiThuong()+" da ton tai");
        if(dto.getGiaTri()<1000000) throw new RuntimeException("Giai thuong phai lon hon 1000000");
        if(dto.getSoLuong()<1) throw new RuntimeException("So luong giai phai >=1");
        if(dto.getSoGiaiCN()+dto.getSoGiaiDN()>dto.getSoLuong()) throw new RuntimeException("giai ca nhan va doanh nghiep k dc lon hon tong so giai");

        GiaiThuong giaiThuong = new GiaiThuong();
        giaiThuong.setGiaiThuong(dto.getGiaiThuong());
        giaiThuong.setGiaTri(dto.getGiaTri());
        giaiThuong.setSoLuong(dto.getSoLuong());
        giaiThuong.setSoGiaiCN(dto.getSoGiaiCN());
        giaiThuong.setSoGiaiDN(dto.getSoGiaiDN());
        KyQuayThuong kyQuayThuong = kyQuayThuongRepository.findById(kyQuayThuongId).orElseThrow(()-> new RuntimeException("Ky quay thuong khong ton tai"));
        giaiThuong.setKyQuayThuong(kyQuayThuong);
        return giaiThuongReppository.save(giaiThuong);
    }

    public GiaiThuong update(UpdateGiaiThuongDTO dto, Long giaithuongId){

        if(dto.getGiaTri()<1000000) throw new RuntimeException("Giai thuong phai lon hon 1000000");
        if(dto.getSoLuong()<1) throw new RuntimeException("So luong giai phai >=1");
        if(dto.getSoGiaiCN()+dto.getSoGiaiDN()>dto.getSoLuong()) throw new RuntimeException("giai ca nhan va doanh nghiep k dc lon hon tong so giai");

        GiaiThuong giaiThuong = giaiThuongReppository.findById(giaithuongId).orElseThrow(()->new RuntimeException("Khong tim thay giai thuong"));
        giaiThuong.setGiaiThuong(dto.getGiaiThuong());
        giaiThuong.setGiaTri(dto.getGiaTri());
        giaiThuong.setSoLuong(dto.getSoLuong());
        giaiThuong.setSoGiaiCN(dto.getSoGiaiCN());
        giaiThuong.setSoGiaiDN(dto.getSoGiaiDN());
        return giaiThuongReppository.save(giaiThuong);

    }
    public GiaiThuong finById(Long id){
        return giaiThuongReppository.findById(id).orElseThrow(()-> new RuntimeException("Khong tim thay giai thuong"));
    }
    public List<GiaiThuong> findByKyQuayThuong(Long kqtId){
        return giaiThuongReppository.findByKQT(kqtId);
    }
    public void delete(Long giaiThuongID){
        giaiThuongReppository.deleteById(giaiThuongID);
    }



}
