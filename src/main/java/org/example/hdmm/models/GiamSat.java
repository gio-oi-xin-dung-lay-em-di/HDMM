package org.example.hdmm.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="BAOPH_HOI_DONG_GIAM_SAT")
public class GiamSat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "STT")
    private Integer STT;
    @Column(name = "GIOI_TINH" , nullable = false)
    private Boolean gioiTinh;
    @Column(name = "HO_TEN" , nullable = false)
    private String hoTen;
    @Column(name = "DON_VI" , nullable = false)
    private String donVi;
    @Column(name = "CHUC_DANH" , nullable = false)
    private String chucDanh;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "KY_QUAY_THUONG")
    private KyQuayThuong kyQuayThuong;


}
