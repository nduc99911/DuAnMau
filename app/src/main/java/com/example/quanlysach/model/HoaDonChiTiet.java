package com.example.quanlysach.model;

public class HoaDonChiTiet {
    private int maHDCT;
    private hoaDon hoaDon;
    private Sach sach;
    private int soLuongMua;

    public HoaDonChiTiet() {

    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" +
                "maHDCT=" + maHDCT +
                ", hoaDon=" + hoaDon +
                ", sach=" + sach +
                ", soLuongMua=" + soLuongMua +
                '}';
    }

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }

    public com.example.quanlysach.model.hoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(com.example.quanlysach.model.hoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public HoaDonChiTiet(int maHDCT, com.example.quanlysach.model.hoaDon hoaDon, Sach sach, int soLuongMua) {
        this.maHDCT = maHDCT;
        this.hoaDon = hoaDon;
        this.sach = sach;
        this.soLuongMua = soLuongMua;
    }
}
