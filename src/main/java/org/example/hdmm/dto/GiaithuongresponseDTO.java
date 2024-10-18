package org.example.hdmm.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.example.hdmm.models.KetQua;
import org.example.hdmm.models.KyQuayThuong;

import java.util.List;

public class GiaithuongresponseDTO {
    private long id;
    private int giaiThuong ;
    private Integer giaTri ;
    private Integer soLuong;
    private Integer soGiaiCN ;
    private Integer soGiaiDN ;
    private KyQuayThuong kyQuayThuong;
    private List<KetQua> ketQuaList;

}
