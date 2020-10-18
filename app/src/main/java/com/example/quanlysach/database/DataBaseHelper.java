package com.example.quanlysach.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.quanlysach.dao.HoaDonChiTietDAO;
import com.example.quanlysach.dao.HoaDonDAO;
import com.example.quanlysach.dao.NguoiDungDAO;
import com.example.quanlysach.dao.SachDAO;
import com.example.quanlysach.dao.TheLoaiDAO;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="dbBookManager";
    public static final int VERSION=1;

    public DataBaseHelper(Context context) {
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NguoiDungDAO.SQL_NGUOI_DUNG);
        db.execSQL(TheLoaiDAO.SQL_THE_LOAI);
        db.execSQL(SachDAO.SQL_SACH);
        db.execSQL(HoaDonDAO.SQL_HOA_DON);
        db.execSQL(HoaDonChiTietDAO.SQL_HOA_DON_CHI_TIET);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+NguoiDungDAO.TABLE_NAME);
        db.execSQL("Drop table if exists "+TheLoaiDAO.TABLE_NAME);
        db.execSQL("Drop table if exists "+ SachDAO.TABLE_NAME);
        db.execSQL("Drop table if exists "+ HoaDonDAO.TABLE_NAME);
        db.execSQL("Drop table if exists "+ HoaDonChiTietDAO.TABLE_NAME);
        onCreate(db);
    }
}
