package org.example.hdmm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FindHoaDonDTO {

    private Integer ky;
    private Date tuNgay;
    private Date denNgay;
    private Integer trangThaiThamGia;
    private String nmMst;
    private String nmTen;
    private String ttHdon;
    private Integer loaiNnt;
    private Integer loaiHd;
    private String kyHieu;
    private String so;
    private String nbMst;
    private String nbTen;
    private String cqt;



}
