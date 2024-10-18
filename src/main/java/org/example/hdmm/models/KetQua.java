package org.example.hdmm.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
    @JsonSerialize(using = ToStringSerializer.class)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "GIAI_THUONG")
    private GiaiThuong giaiThuong;

    @OneToOne()
    @JoinColumn(name = "HDON")
    private HoaDon hoaDon;
}
