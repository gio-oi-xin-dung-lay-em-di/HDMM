package org.example.hdmm.repository;

import org.example.hdmm.models.HoaDon;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {

    @Query(value = "select * from baoph_ds_hdon",nativeQuery = true)
    List<HoaDon> find10HoaDon(Pageable pageable);

    @Query("UPDATE HoaDon hd SET hd.ky = :ky WHERE hd.tdlap BETWEEN :startDate AND :endDate")
    void setKy(@Param("startDate")Date startDate , @Param("endDate") Date endDate , @Param("ky")Long ky);


}
