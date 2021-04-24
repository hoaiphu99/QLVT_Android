package com.example.qlvt.QLTaiXe;

public class TaiXe {
    String maTaiXe, tenTaiXe, ngaySinh, diaChi;

    public TaiXe() {
    }

    public TaiXe(String maTaiXe, String tenTaiXe, String ngaySinh, String diaChi) {
        this.maTaiXe = maTaiXe;
        this.tenTaiXe = tenTaiXe;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
    }

    public String getMaTaiXe() {
        return maTaiXe;
    }

    public void setMaTaiXe(String maTaiXe) {
        this.maTaiXe = maTaiXe;
    }

    public String getTenTaiXe() {
        return tenTaiXe;
    }

    public void setTenTaiXe(String tenTaiXe) {
        this.tenTaiXe = tenTaiXe;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
