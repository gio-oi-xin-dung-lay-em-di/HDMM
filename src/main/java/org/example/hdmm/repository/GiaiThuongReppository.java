package org.example.hdmm.repository;

import org.example.hdmm.models.GiaiThuong;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface GiaiThuongReppository extends JpaRepository<GiaiThuong,Long> {
    @Query("select g from GiaiThuong g where g.kyQuayThuong.id = :kqt and g.giaiThuong = :giai")
    List<GiaiThuong> listByGiaiAndKy (@Param("kqt") Long kyQuayThuong,Integer giai);
    @Query("select g from GiaiThuong g where g.kyQuayThuong.id = :kqt order by g.giaiThuong")
    List<GiaiThuong> findByKQT (@Param("kqt") Long kyQuayThuong);







}
