package com.example.quanlysach.model;

public class NguoiDung {
    private String username;
    private String password;
    private String phone;
    private String hoten;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHoTen() {
        return hoten;
    }

    public void setHoTen(String hoten) {
        this.hoten = hoten;
    }

    public NguoiDung(String username, String password, String phone, String hoten) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.hoten = hoten;
    }

    public NguoiDung() {
    }

    @Override
    public String toString() {
        return "NguoiDung{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", hoten='" + hoten + '\'' +
                '}';
    }
}
