package org.example.hdmm.quaythuong;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.hdmm.models.GiaiThuong;
import org.example.hdmm.models.HoaDon;
import org.example.hdmm.models.KyQuayThuong;
import org.example.hdmm.service.HoaDonService;
import org.example.hdmm.service.KetQuaService;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class quaythuong implements Runnable {

    private Integer number;
    private Integer loainnt;
    private KyQuayThuong kqt;
    private GiaiThuong giaiThuong;
    private HoaDonService hoaDonService;  // Injected
    private KetQuaService ketQuaService;  // Injected

    @Override
    public void run() {
        try {
            HoaDon hd = hoaDonService.hoadonmayman(kqt.getCoQuanThue().getCqt(), kqt.getTuNgay(), kqt.getDenNgay(), loainnt, number);
            ketQuaService.create(giaiThuong, hd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

