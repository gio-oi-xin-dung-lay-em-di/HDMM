package org.example.hdmm.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BAOPH_TTIN_GIAI_THUONG")
public class GiaiThuong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "GIAI_THUONG")
    private int giaiThuong = 1;
    @Column(name = "GIA_TRI")
    private Integer giaTri = 1000000;
    @Column(name = "SO_LUONG")
    private Integer soLuong;
    @Column(name = "SO_GIAI_CN")
    private Integer soGiaiCN = 0;
    @Column(name = "SO_GIAI_DN")
    private Integer soGiaiDN = 0;

    @ManyToOne
    @JoinColumn(name = "KY_QUAY_THUONG")
    @JsonIgnore
    private KyQuayThuong kyQuayThuong;



    @OneToMany(mappedBy = "giaiThuong",fetch = FetchType.LAZY)
    private List<KetQua> ketQuaList;



}
