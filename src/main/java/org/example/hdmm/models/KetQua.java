package org.example.hdmm.models;

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
@Table(name = "BAOPH_KET_QUA_QUAY_THUONG")
public class KetQua {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "GIAI_THUONG")
    private GiaiThuong giaiThuong;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "HDON")
    private HoaDon hoaDon;







}
