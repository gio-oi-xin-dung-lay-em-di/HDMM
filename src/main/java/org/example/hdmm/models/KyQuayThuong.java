package org.example.hdmm.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import javax.lang.model.element.Name;
import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "BAOPH_KY_QUAY_THUONG")
public class KyQuayThuong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "MA_KY_QUAY_THUONG",precision = 4,nullable = false)
    private Integer maKy;
    @Column(name = "TEN_KY_QUAY_THUONG",length = 100, nullable = false)
    private String tenKy;
    @Column(name = "TU_NGAY",nullable = false)
    private Date tuNgay;
    @Column(name = "DEN_NGAY", nullable = false)
    private Date denNgay;

    @Column(name = "TONG_SO")
    private Integer tongSo;
    @Column(name = "DN_DU_DK")
    private Integer dnDuDK;
    @Column(name = "CN_DU_DK")
    private Integer cnDuDK;
    @Column(name = "DN_KO_DU_DK")
    private Integer dnKoDuDK;
    @Column(name = "CN_KO_DU_DK")
    private Integer cnKoDuDK;
    @Column(name = "NGAY_QUAY_THUONG")
    private Date ngayLuaChon;
    @Column(name = "T_THAI", precision = 1)
    private Integer status;
//    Đang cấu hình
//    Chờ xử lý số liệu
//    Đã xử lý số liệu
//    Đã lựa chọn HDMM

    @ManyToOne
    @JoinColumn(name = "CQT")
    private CoQuanThue coQuanThue;

    @JsonIgnore
    @OneToMany(mappedBy = "kyQuayThuong",fetch = FetchType.LAZY)
    private List<GiamSat> giamSat;

    @JsonIgnore
    @OneToMany(mappedBy = "kyQuayThuong",fetch = FetchType.LAZY)
    private List<GiaiThuong> giaiThuongList;



    public KyQuayThuong( String tenKy, Date tuNgay, Date denNgay, int status) {

        this.tenKy = tenKy;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.status = status;
    }
}
