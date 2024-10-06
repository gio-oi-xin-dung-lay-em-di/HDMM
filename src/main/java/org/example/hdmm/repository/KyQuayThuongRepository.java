package org.example.hdmm.repository;

import org.example.hdmm.models.KyQuayThuong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface KyQuayThuongRepository extends JpaRepository<KyQuayThuong, Long> {
    @Query("SELECT CASE WHEN COUNT (e) >0 THEN true ELSE false END FROM KyQuayThuong e WHERE e.maKy = :maKy AND e.coQuanThue.cqt = :cqt")
    boolean existsByMaKyAndCoQuanThue_Id(@Param("maKy") Integer maKy,@Param("cqt") Long cqt);
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM KyQuayThuong e " +
            "WHERE e.tuNgay <= :date AND e.denNgay >= :date AND e.coQuanThue.cqt = :cqt ")
    boolean existsByDate(@Param("date") Date date , @Param("cqt") Long cqt);
    @Query("SELECT CASE WHEN COUNT(e) >0 THEN true ELSE false END FROM KyQuayThuong e " +
            "WHERE e.tuNgay <= :date AND e.denNgay >= :date AND e.coQuanThue.cqt = :cqt AND e.id != :id ")
    boolean exists1ByDate(@Param("date") Date date , @Param("cqt") Long cqt,@Param("id")Long id);
    @Query("select e from KyQuayThuong e where e.coQuanThue.cqt = :cqt")
    List<KyQuayThuong> findAllByCQT(@Param("cqt") Integer cqt);

}
