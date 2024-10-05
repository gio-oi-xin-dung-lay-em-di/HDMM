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
public class UpdateKQTDTO {
    private Integer id;
    private String tenKy;
    private Date tuNgay;
    private Date denNgay;
}
