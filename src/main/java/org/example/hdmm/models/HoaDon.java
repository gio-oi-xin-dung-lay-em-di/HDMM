package org.example.hdmm.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BAOPH_DS_HDON")
public class HoaDon {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MAHD", length = 36)
    private String mahd;

    @Column(name = "KHMHD", precision = 1)
    private Integer khmhd;

    @Column(name = "KHHD", length = 50)
    private String khhd;

    @Column(name = "SOHD", length = 50)
    private String sohd;

    @Column(name = "NBMST", length = 14)
    private String nbmst;

    @Column(name = "NBTEN", length = 200)
    private String nbTen;

    @Column(name = "NMMST", length = 14)
    private String nmmst;

    @Column(name = "NMTEN", length = 200)
    private String nmTen;

    @Column(name = "TDLAP")
    @Temporal(TemporalType.DATE)
    private Date tdlap;

    @Column(name = "TTHAI", length = 2)
    private String tthai;

    @Column(name = "TTXLY", length = 2)
    private String ttxly;

    @Column(name = "LOAI_NNT", precision = 1)
    private Integer loaiNnt;

    @Column(name = "TAXO", length = 4)
    private String taxo;


    @Column(name = "KY", precision = 4)
    private Integer ky;

    @Column(name = "STT")
    private Integer stt;

    @Column(name = "IS_QUALIFIED", precision = 1)
    private Integer isQualified;

    @Column(name = "NGUOI_TH", length = 50)
    private String nguoiTh;

    @Column(name = "NGAY_TH")
    @Temporal(TemporalType.DATE)
    private Date ngayTh;

    @Column(name = "LYDO", length = 500)
    private String lydo;

    @Column(name = "STA_UPDATE", length = 1)
    private String staUpdate;

    @OneToOne(mappedBy = "hoaDon",fetch = FetchType.LAZY)
    private KetQua ketQua;

    @ManyToOne()
    @JoinColumn(name = "CQT")

    private CoQuanThue coQuanThue;



}
