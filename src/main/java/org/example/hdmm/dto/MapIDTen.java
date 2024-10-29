package org.example.hdmm.dto;

public class MapIDTen {

    public static String getTenLoaiHdon(int id){
        switch (id){
            case 1: return "Hóa đơn giá trị gia tăng";
            case 2: return "Hóa đơn bán hàng";
            case 3: return "Hóa đơn bán tài sản công";
            case 4: return "Hóa đơn bán hàng dự trữ quốc gia";
            case 5: return "Hóa đơn khác";
            case 6: return "Các chứng từ được in, phát hành, sử dụng và quản lí như hóa đơn";
            default: return "";
        }

    }
    public static String getTenTThai(String id){
        switch (id){
            case "1": return "Hóa đơn gốc";
            case "2": return "Hóa đơn thay thế";
            case "3": return "Hóa đơn điều chỉnh";
            case "4": return "Hóa đơn đã bị thay thế";
            case "5": return "Hóa đơn đã bị điều chỉnh";
            case "6": return "Hóa đơn đã bị xóa bỏ/ Hủy bỏ";
            case "7": return "Khác";
            default: return "";

        }
    }
}
