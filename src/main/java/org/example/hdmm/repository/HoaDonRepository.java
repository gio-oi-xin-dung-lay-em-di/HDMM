package org.example.hdmm.repository;

import org.example.hdmm.dto.FindHoaDonDTO;
import org.example.hdmm.models.HoaDon;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {

    @Query(value = "select * from baoph_ds_hdon",nativeQuery = true)
    List<HoaDon> find10HoaDon(Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE HoaDon hd SET hd.ky = :ky WHERE hd.tdlap BETWEEN :startDate AND :endDate ")
    void setAllKy(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("ky") Integer ky);
    @Query(value = "SELECT " +
            "    SUM(CASE WHEN is_qualified = 1 AND loai_nnt = 1 THEN 1 ELSE 0 END) AS dndudk, " +
            "    SUM(CASE WHEN is_qualified = 1 AND loai_nnt != 1 THEN 1 ELSE 0 END) AS cndudk, " +
            "    SUM(CASE WHEN is_qualified != 1 AND loai_nnt = 1 THEN 1 ELSE 0 END) AS dnkodudk, " +
            "    SUM(CASE WHEN is_qualified != 1 AND loai_nnt != 1 THEN 1 ELSE 0 END) AS cnkodudk " +
            "FROM baoph_ds_hdon  " +
            "WHERE cqt = :cqt " +
            "AND tdlap BETWEEN TO_DATE(:startDate, 'dd-mm-yyyy') AND TO_DATE(:endDate, 'dd-mm-yyyy')",
            nativeQuery = true)
    List<Object[]> countData(@Param("cqt") String cqt, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("select h from HoaDon h where " +
            "(:ky is null or h.ky = :ky) and " +
            "( (:startDate is null and :endDate is null) or h.tdlap between :startDate and :endDate ) and " +
            "(:isQualified is null or h.isQualified = :isQualified) and " +
            "(:nmMst is null or h.nmmst = :nmMst) and " +
            "(:nmTen is null or h.nmTen like %:nmTen%) and " +
            "(:tThai is null or h.tthai = :tThai) and " +
            "(:loaiNnt is null or h.loaiNnt = :loaiNnt) and " +
            "(:loaiHd is null or 1=1) and " +
            "(:kyHieu is null or h.khhd = :kyHieu) and " +
            "(:sohd is null or h.sohd = :sohd) and " +
            "(:nbMst is null or h.nbmst = :nbMst) and " +
            "(:nbTen is null or h.nbTen like %:nbTen%) and " +
            "(:cqt is null or h.coQuanThue.cqt = :cqt) "
    )
    List<HoaDon> traCuuHoaDon (Integer ky, Date startDate, Date endDate, Integer isQualified,
                               String nmMst, String nmTen, String tThai, Integer loaiNnt,
                               Integer loaiHd, String kyHieu, String sohd, String nbMst,
                               String nbTen, String cqt, Pageable pageable);
    @Query("select count(h) from HoaDon h where " +
            "(:ky is null or h.ky = :ky) and " +
            "( (:startDate is null and :endDate is null) or h.tdlap between :startDate and :endDate ) and " +
            "(:isQualified is null or h.isQualified = :isQualified) and " +
            "(:nmMst is null or h.nmmst = :nmMst) and " +
            "(:nmTen is null or h.nmTen like %:nmTen%) and " +
            "(:tThai is null or h.tthai = :tThai) and " +
            "(:loaiNnt is null or h.loaiNnt = :loaiNnt) and " +
            "(:loaiHd is null or 1=1) and " +
            "(:kyHieu is null or h.khhd = :kyHieu) and " +
            "(:sohd is null or h.sohd = :sohd) and " +
            "(:nbMst is null or h.nbmst = :nbMst) and " +
            "(:nbTen is null or h.nbTen like %:nbTen%) and " +
            "(:cqt is null or h.coQuanThue.cqt = :cqt)"
    )
    Integer countTraCuuHoaDon (Integer ky, Date startDate, Date endDate, Integer isQualified,
                               String nmMst, String nmTen, String tThai, Integer loaiNnt,
                               Integer loaiHd, String kyHieu, String sohd, String nbMst,
                               String nbTen, String cqt);


    @Query("select h from HoaDon h " +
            "where h.isQualified = 1 and (:loaiNnt is null or h.loaiNnt = :loaiNnt) and h.coQuanThue.cqt = :cqt and h.tdlap between :startDate and :endDate "
    )
    List<HoaDon> quayThuong(String cqt ,Date startDate , Date endDate , Integer loaiNnt ,Pageable pageable);







}
