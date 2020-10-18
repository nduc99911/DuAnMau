package com.example.quanlysach.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quanlysach.database.DataBaseHelper;
import com.example.quanlysach.model.HoaDonChiTiet;
import com.example.quanlysach.model.Sach;
import com.example.quanlysach.model.hoaDon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietDAO {
    private SQLiteDatabase db;
    private DataBaseHelper dbHelper;
    public static final String TABLE_NAME = "HoaDonChiTiet";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static final String SQL_HOA_DON_CHI_TIET = "CREATE TABLE HoaDonChiTiet(maHDCT INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "mahoadon text NOT NULL, masach text NOT NULL, soluong INTEGER);";
    public static final String TAG = "HoaDonChiTiet";

    public HoaDonChiTietDAO(Context context) {
        dbHelper = new DataBaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<HoaDonChiTiet> getAllHoaDonChiTietByID(String maHoaDon) {
        List<HoaDonChiTiet> dsHoaDonChiTiet = new ArrayList<>();
        String sSQL = "SELECT maHDCT, hoadon.mahoadon,hoadon.ngaymua,Sach.masach, Sach.matheloai, Sach.tensach, Sach.tacgia, Sach.nhaxuatban, Sach.giabia,Sach.soluong,HoaDonChiTiet.soluong FROM HoaDonChiTiet INNER JOIN hoadon on HoaDonChiTiet.mahoadon = HoaDon.mahoadon INNER JOIN Sach on Sach.masach = HoaDonChiTiet.masach where HoaDonChiTiet.mahoadon='" + maHoaDon + "'";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        try {
            while (c.isAfterLast() == false) {
                HoaDonChiTiet ee = new HoaDonChiTiet();
                ee.setMaHDCT(c.getInt(0));
                ee.setHoaDon(new hoaDon(c.getString(1), sdf.parse(c.getString(2))));
                ee.setSach(new Sach(c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getDouble(8), c.getInt(9)));
                ee.setSoLuongMua(c.getInt(10));
                dsHoaDonChiTiet.add(ee);
                Log.d("//=====", ee.toString());
                c.moveToNext();
            }
            c.close();
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return dsHoaDonChiTiet;
    }

    //insert
    public int inserHoaDonChiTiet(HoaDonChiTiet hd) {
        ContentValues values = new ContentValues();
        values.put("mahoadon", hd.getHoaDon().getMaHoaDon());
        values.put("masach", hd.getSach().getMaSach());
        values.put("soluong", hd.getSoLuongMua());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    //delete
    public int DeleteHDCTById(String mahoadon) {
        int result = db.delete(TABLE_NAME, "maHDCT=?", new String[]{mahoadon});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public double getDoanhThuTheoNgay() {
        double doanhThu = 0;
        String sSQL = "SELECT SUM(tongtien) from (SELECT SUM(sach.giabia * HoaDonChiTiet.soluong) as 'tongtien' " + "FROM hoadon INNER JOIN HoaDonChiTiet on hoadon.mahoadon = HoaDonChiTiet.mahoadon " + "INNER JOIN sach on HoaDonChiTiet.masach = sach.masach where hoadon.ngaymua = date('now') GROUP BY HoaDonChiTiet.maSach)tmp";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }

    public double getDoanhThuTheoThang() {
        double doanhThu = 0;
        String sSQL = "SELECT SUM(tongtien) from (SELECT SUM(sach.giabia * HoaDonChiTiet.soluong) as 'tongtien' " + "FROM hoadon INNER JOIN HoaDonChiTiet on hoadon.mahoadon = HoaDonChiTiet.mahoadon " + "INNER JOIN Sach on HoaDonChiTiet.masach = Sach.masach where strftime('%m',hoadon.ngaymua) = strftime('%m','now') GROUP BY HoaDonChiTiet.masach)tmp";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }

    public double getDoanhThuTheoNam() {
        double doanhThu = 0;
        String sSQL = "SELECT SUM(tongtien) from (SELECT SUM(Sach.giabia * HoaDonChiTiet.soluong) as 'tongtien' " + "FROM HoaDon INNER JOIN HoaDonChiTiet on hoadon.mahoadon = HoaDonChiTiet.mahoadon " + "INNER JOIN Sach on HoaDonChiTiet.masach = Sach.masach where strftime('%Y',hoadon.ngaymua) = strftime('%Y','now') GROUP BY HoaDonChiTiet.masach)tmp";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }

    public boolean checkHoaDon(String maHoaDon) {
        //SELECT
        String[] columns = {"mahoadon"};
        //WHERE clause
        String selection = "mahoadon=?";
        //WHERE clause arguments
        String[] selectionArgs = {maHoaDon};
        Cursor c = null;
        try {
            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if (i <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
