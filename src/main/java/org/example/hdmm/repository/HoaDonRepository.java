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
    @Query("UPDATE HoaDon hd SET hd.ky = :ky WHERE hd.tdlap BETWEEN :startDate AND :endDate AND hd.coQuanThue.cqt = :cqt")
    void setAllKy(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("ky") Integer ky,String cqt);



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
            "(:loaiHd is null or h.khmhd = :loaiHd) and " +
            "(:kyHieu is null or h.khhd = :kyHieu) and " +
            "(:sohd is null or h.sohd = :sohd) and " +
            "(:nbMst is null or h.nbmst = :nbMst) and " +
            "(:nbTen is null or h.nbTen like %:nbTen%) and " +
            "(:cqt is null or h.coQuanThue.cqt = :cqt) order by h.tdlap"
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
            "(:loaiHd is null or h.khmhd = :loaiHd) and " +
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

    @Transactional
    @Modifying
    @Query(value = "MERGE INTO baoph_ds_hdon target " +
            "USING ( " +
            "    SELECT id, ROW_NUMBER() OVER (ORDER BY loai_nnt DESC) AS new_stt " +
            "    FROM baoph_ds_hdon hd " +
            "    WHERE hd.cqt = :cqt " +
            "      AND hd.is_qualified = 1 " +
            "      AND (:loaiNnt IS NULL OR hd.loai_nnt = :loaiNnt) " +
            "      AND (hd.tdlap BETWEEN :startDate AND :endDate) " +
            ") temp " +
            "ON (target.id = temp.id) " +
            "WHEN MATCHED THEN " +
            "UPDATE SET target.stt = temp.new_stt",
            nativeQuery = true)
    void updateStt(String cqt, Integer loaiNnt, Date startDate, Date endDate);
    @Transactional
    @Modifying
    @Query("update HoaDon h set h.stt = null ")
    void resetStt();

    @Transactional
    @Modifying
    @Query("UPDATE HoaDon h SET h.lydo = :lydo, h.isQualified = 3 WHERE h.id IN :list")
    void loaiBo(@Param("lydo") String lydo, @Param("list") Integer[] list);


    @Transactional
    @Modifying
    @Query("UPDATE HoaDon h SET h.lydo = null, h.isQualified = 1 WHERE h.id IN :list")
    void chapNhan(@Param("list") Integer[] list);



    @Transactional
    @Modifying
    @Query("UPDATE HoaDon h SET h.lydo = :lydo, h.isQualified = 3 WHERE h.id = :id ")
    void updateQualified(Integer id , String lydo);


}
