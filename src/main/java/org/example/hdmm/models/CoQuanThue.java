package org.example.hdmm.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
@Table(name = "BAOPH_CQT")
public class CoQuanThue {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CQT")
    private String cqt;
    @Column(nullable = false,name = "TEN_CQT")
    private String tenCQT;
    @JsonIgnore
    @OneToMany(mappedBy = "coQuanThue",fetch = FetchType.LAZY)
    private List<KyQuayThuong> listKyQuayThuong;
    @JsonIgnore
    @OneToMany(mappedBy = "coQuanThue",fetch = FetchType.LAZY)
    private List<HoaDon> hoaDonList;
}
