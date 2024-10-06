package org.example.hdmm.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GiamSatDTO {
    private Integer stt;
    private Integer gioiTinh;
    private String hoTen;
    private String donVi;
    private String chucDanh;
}
