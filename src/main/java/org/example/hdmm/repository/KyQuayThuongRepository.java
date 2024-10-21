package org.example.hdmm.repository;

import org.example.hdmm.models.KyQuayThuong;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

public interface KyQuayThuongRepository extends JpaRepository<KyQuayThuong, Long> {
    @Query("SELECT CASE WHEN COUNT (e) >=1 THEN true ELSE false END FROM KyQuayThuong e WHERE e.maKy = :maKy AND e.coQuanThue.cqt = :cqt")
    boolean existsByMaKyAndCoQuanThue_Id(@Param("maKy") Integer maKy,@Param("cqt") String cqt);
    @Query("SELECT CASE WHEN COUNT(e) > 1 THEN true ELSE false END FROM KyQuayThuong e " +
            "WHERE e.tuNgay <= :date AND e.denNgay >= :date AND e.coQuanThue.cqt = :cqt ")
    boolean existsByDate(@Param("date") Date date , @Param("cqt") String cqt);
    @Query("SELECT CASE WHEN COUNT(e) >0 THEN true ELSE false END FROM KyQuayThuong e " +
            "WHERE e.tuNgay <= :date AND e.denNgay >= :date AND e.coQuanThue.cqt = :cqt AND e.id != :id ")
    boolean exists1ByDate(@Param("date") Date date , @Param("cqt") String cqt,@Param("id")Long id);
    @Query("select e from KyQuayThuong e where e.coQuanThue.cqt = :cqt order by e.maKy desc ")
    List<KyQuayThuong> findAllByCQT(@Param("cqt") String cqt, Pageable pageable);
    @Query("select e from KyQuayThuong e where e.coQuanThue.cqt = :cqt and e.status = 3 order by e.maKy desc ")
    List<KyQuayThuong> findAllByCQTAndStatus(@Param("cqt") String cqt);

    @Query("select e from KyQuayThuong e where e.coQuanThue.cqt = :cqt order by e.maKy desc ")
    List<KyQuayThuong> findAllByCQT2(@Param("cqt") String cqt);

    @Query("SELECT CASE WHEN COUNT (e) >=1 THEN true ELSE false END FROM KyQuayThuong e WHERE e.maKy = :maKy")
    boolean existsByMaKy(@Param("maKy") Integer maKy);
    @Query("select count(e) from KyQuayThuong e where e.coQuanThue.cqt = :cqt")

    Integer countByCQT(@Param("cqt") String cqt);

    @Transactional
    @Modifying
    @Query("UPDATE KyQuayThuong k SET k.status = 3")
    void resetStatus();

}
