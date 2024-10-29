package org.example.hdmm.service;

import org.example.hdmm.dto.KyQuayThuongDTO;
import org.example.hdmm.dto.UpdateKQTDTO;
import org.example.hdmm.models.*;
import org.example.hdmm.quaythuong.quaythuong;
import org.example.hdmm.repository.CoQuanThueRepository;
import org.example.hdmm.repository.GiaiThuongReppository;
import org.example.hdmm.repository.KetQuaRepository;
import org.example.hdmm.repository.KyQuayThuongRepository;
import org.example.hdmm.util.RandomArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


@Service
public class KyQuayThuongService {
    @Autowired
    private KyQuayThuongRepository kyQuayThuongRepository;
    @Autowired
    private CoQuanThueRepository coQuanThueRepository;
    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private GiaiThuongService giaiThuongService;
    @Autowired
    private KetQuaService ketQuaService;
    public KyQuayThuong  getKyQuayThuongById(Integer id){
        return kyQuayThuongRepository.findById(Long.valueOf(id)).orElseThrow(()->new RuntimeException("Khong tim thay ky quay thuong"));
    }

    public List<KyQuayThuong> getAllKyQuayThuongByCQT(String cqt, Pageable pageable) {
        return kyQuayThuongRepository.findAllByCQT(cqt,pageable);
    }
    public List<KyQuayThuong> getAllKyQuayThuongByCQTAndStatus(String cqt) {
        return kyQuayThuongRepository.findAllByCQTAndStatus(cqt);
    }


    // create
    public KyQuayThuong create(KyQuayThuongDTO dto,String cqtId) {
        if(dto.getMaKy()==null) throw new RuntimeException("kyNull");
        if(kyQuayThuongRepository.existsByMaKy(dto.getMaKy())) throw new RuntimeException("maKyExist");

        if(dto.getTenKy()==null||dto.getTenKy().isEmpty()) throw new RuntimeException("tenKyNull");

        if(dto.getTuNgay()==null) throw new RuntimeException("startDateNull");
        if(kyQuayThuongRepository.existsByDate(dto.getTuNgay(), cqtId)) throw new RuntimeException("startDateInvalid");

        if(dto.getDenNgay()==null) throw new RuntimeException("endDateNull");
        if(kyQuayThuongRepository.existsByDate(dto.getDenNgay(),cqtId)) throw new RuntimeException("endDateInvalid");

        if(dto.getTuNgay().compareTo(dto.getDenNgay())>0) throw new RuntimeException("start_endDateInvalid");
//        hoaDonService.setAllKy(dto.getTuNgay(),dto.getDenNgay(),dto.getMaKy());
        KyQuayThuong kqt = new KyQuayThuong();
        kqt.setMaKy(dto.getMaKy());
        kqt.setTenKy(dto.getTenKy());
        kqt.setTuNgay(dto.getTuNgay());
        kqt.setDenNgay(dto.getDenNgay());
        kqt.setStatus(1);
        Random random = new Random();

        long randomLong = (long) (random.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
        kqt.setId(randomLong);

        CoQuanThue cqt =coQuanThueRepository.findById(cqtId)
                .orElseThrow(() -> new RuntimeException("Ko tim thay 2 cqt"));

        kqt.setCoQuanThue(cqt);

        return kyQuayThuongRepository.save(kqt);

    }
 //update
    public KyQuayThuong update(Long id , UpdateKQTDTO dto){


        KyQuayThuong kqt = kyQuayThuongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("kqtNotFound"));
        if(kqt.getTuNgay() == dto.getTuNgay() ||kqt.getDenNgay() == dto.getDenNgay()) throw new RuntimeException("date_duplicated");
        if(dto.getTuNgay().compareTo(dto.getDenNgay())>=0) throw new RuntimeException("start_endDateInvalid");

        if(kyQuayThuongRepository.existsByDate(dto.getTuNgay(), kqt.getCoQuanThue().getCqt())) throw new RuntimeException("startDateInvalid");
        if(kyQuayThuongRepository.existsByDate(dto.getDenNgay(), kqt.getCoQuanThue().getCqt())) throw new RuntimeException("endDateInvalid");
        if(dto.getTenKy().isEmpty()||dto.getTenKy().length()>100) throw new RuntimeException("tenInvalid");
        kqt.setTenKy(dto.getTenKy());
        kqt.setTuNgay(dto.getTuNgay());
        kqt.setDenNgay(dto.getDenNgay());
        return kyQuayThuongRepository.save(kqt);

    }
    public KyQuayThuong xuli(Long id){
        KyQuayThuong kqt = kyQuayThuongRepository.findById(id).orElseThrow(() -> new RuntimeException("kqtNotFound"));
        List<GiaiThuong> gtList = kqt.getGiaiThuongList();
        if(gtList.size()==0) throw new RuntimeException("koCoGiaiThuong");
        int cn =0;
        int dn =0;
        for(GiaiThuong gt:gtList){
            cn += gt.getSoGiaiCN();
            dn += gt.getSoGiaiDN();
        }
        if(cn==0 && dn!=0) hoaDonService.updateStt(kqt.getCoQuanThue().getCqt(),2,kqt.getTuNgay(),kqt.getDenNgay());
        else if (dn==0 && cn!=0) hoaDonService.updateStt(kqt.getCoQuanThue().getCqt(),2,kqt.getTuNgay(),kqt.getDenNgay());
        else hoaDonService.updateStt(kqt.getCoQuanThue().getCqt(),null,kqt.getTuNgay(),kqt.getDenNgay());

        Object[] countData = hoaDonService.countData(kqt.getCoQuanThue().getCqt(),kqt.getTuNgay(),kqt.getDenNgay()).get(0);
        BigDecimal dnDuDk = countData[0]==null?new BigDecimal(0):(BigDecimal) countData[0];
        BigDecimal cnDuDk =countData[1]==null?new BigDecimal(0):(BigDecimal) countData[1];
        BigDecimal dnKoDuDk = countData[2]==null?new BigDecimal(0):(BigDecimal) countData[2];
        BigDecimal cnKoDuDk = countData[3]==null?new BigDecimal(0):(BigDecimal) countData[3];
        BigDecimal total =  dnDuDk.add(dnKoDuDk).add(cnKoDuDk).add(cnDuDk);
        kqt.setCnDuDK(cnDuDk.intValue());
        kqt.setCnKoDuDK(cnKoDuDk.intValue());
        kqt.setDnDuDK(dnDuDk.intValue());
        kqt.setDnKoDuDK(dnKoDuDk.intValue());
        kqt.setTongSo(total.intValue());
        kqt.setStatus(3);
        hoaDonService.setAllKy(kqt.getTuNgay(),kqt.getDenNgay(),kqt.getMaKy(),kqt.getCoQuanThue().getCqt());
        return kyQuayThuongRepository.save(kqt);
    }

    // Delete
    public void delete(Long id) {
        kyQuayThuongRepository.deleteById(id);
    }
    public KyQuayThuong updateStatus(Long id,Integer status) {
        KyQuayThuong kqt =  kyQuayThuongRepository.findById(id).orElseThrow(()->new RuntimeException("kqtNotFound"));
        kqt.setStatus(status);
        return kyQuayThuongRepository.save(kqt);
    }

    public void resetStatus() {

         kyQuayThuongRepository.resetStatus();
    }

    public Integer countTotal(String cqtId){
        return kyQuayThuongRepository.countByCQT(cqtId);
    }


    private KetQuaRepository ketQuaRepository;
    public List<GiaiThuong> quayThuong(Long kqtId){
        KyQuayThuong kqt = kyQuayThuongRepository.findById(kqtId).orElseThrow(()->new RuntimeException("kqtNotFound"));
        if(kqt.getStatus()==4) throw new RuntimeException("daquaythuong");
        List<GiaiThuong> giaiThuongList = kqt.getGiaiThuongList();
        List<Thread> threads = new ArrayList<>();
        Set<Integer> listcn = new HashSet<>();
        Set<Integer> listdn = new HashSet<>();
        for(GiaiThuong giaiThuong: giaiThuongList){
            if(giaiThuong.getSoLuong()==0) throw new RuntimeException("so luong giai = 0");
            int[] arrCn;
            int[] arrDn;
            if(giaiThuong.getSoGiaiCN()!=0&& giaiThuong.getSoGiaiDN()!=0){
                arrCn = RandomArray.getRandomNumbers(0,kqt.getCnDuDK()-1,giaiThuong.getSoGiaiCN(),listcn);
                arrDn = RandomArray.getRandomNumbers(0,kqt.getDnDuDK()-1,giaiThuong.getSoGiaiDN(),listdn);
            }else{
                int cn = RandomArray.getRandomNumbers(0,kqt.getTongSo(),1)[0];
                arrCn = RandomArray.getRandomNumbers(0,kqt.getCnDuDK()-1,cn,listcn);
                arrDn = RandomArray.getRandomNumbers(0,kqt.getDnDuDK()-1,giaiThuong.getSoLuong()-cn,listdn);
            }
            for(int a:arrCn){
                    Thread thread = new Thread(new quaythuong(a,2,kqt,giaiThuong,hoaDonService,ketQuaService));
                    thread.start();
                    threads.add(thread);
                    listcn.add(a);
            }
            for(int a:arrDn){
                    Thread thread = new Thread(new quaythuong(a,1,kqt,giaiThuong,hoaDonService,ketQuaService));
                    thread.start();
                    threads.add(thread);
                    listdn.add(a);
            }


        }
        try {
            for(Thread t: threads){
                t.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<GiaiThuong> list = giaiThuongService.findByKyQuayThuong(kqt);
        kqt.setStatus(4);
        kyQuayThuongRepository.save(kqt);
        return list;
    }


}
