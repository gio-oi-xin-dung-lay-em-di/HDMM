package org.example.hdmm.repository;

import org.example.hdmm.models.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {

    @Query(value = "select * from baoph_ds_hdon",nativeQuery = true)
    List<HoaDon> find10HoaDon();
}
