package org.example.hdmm.repository;

import org.example.hdmm.models.GiamSat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GiamSatRepository extends JpaRepository<GiamSat, Long> {

    List<GiamSat> getGiamSatsByKyQuayThuong_IdAndSTT(Long ky,Integer stt);
    List<GiamSat> getGiamSatsByKyQuayThuong_Id(Long ky);
}
