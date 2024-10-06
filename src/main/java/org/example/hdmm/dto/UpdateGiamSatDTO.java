package org.example.hdmm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateGiamSatDTO {

    private Integer gioiTinh;
    private String hoTen;
    private String donVi;
    private String chucDanh;

}
