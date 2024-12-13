package com.vunlph30245.duanmau_libmana.Database;

public class Data_SQLite {
    public static  final String INSERT_THUTHU = "insert into ThuThu(maTT,hoTen,matKhau) values" +
            "('admin', 'nguyen admin', 'admin')," +
            "('namvn', 'nguyen van nam', '123456')," +
            "('huy', 'huy', 'huy')";
    public static  final String INSERT_THANHVIEN = "insert into ThanhVien(hoTen,namSinh) values" +
            "('Nguyễn Long Vũ','2003')," +
            "('Le Duc Hieu','2002')," +
            "('Vu Thi Cham','2002')," +
            "('Nguyen Sy Hung','2002')," +
            "('Dao Van Manh','2002')";
    public static final String INSERT_LOAISACH = "insert into LoaiSach(tenLoai) values" +
            "('Tiếng Anh')," +
            "('C++')," +
            "('JavaScrip Cơ bản')," +
            "('Android 1'),"+
            "('java 1'),"+
            "('java 2')";


    public static final String INSERT_SACH = "insert into Sach(TenSach,GiaThue,MaLoai,Nhacungcap) values" +
            "('Đời sống','1000','1','Dân trí')," +
            "('C++ Cơ bản','2000','2','FPT Polytechnic')," +
            "('Java Scrip','3000','3','FPT Polytechnic')," +
            "('Android nâng cao','4000','4','FPT Polytechnic')";
    public static final String INSERT_PHIEUMUON = "insert into PhieuMuon(maTT,maTV,maSach,tienThue,ngay,traSach) values" +
            "('admin','1','1','2000','2021/07/07',1)," +
            "('admin','2','2','2500','2021/07/08',0)," +
            "('admin','3','3','3000','2021/07/09',1)" ;
//            "('admin','4','4','3200','2021/07/10',0)"+
//            "('admin','4','4','3600','2021/07/11',1)";
}
