package org.example.hdmm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateGiaiThuongDTO {
    private int giaiThuong ;
    private Integer giaTri ;
    private Integer soLuong;
    private Integer soGiaiCN ;
    private Integer soGiaiDN ;
}
