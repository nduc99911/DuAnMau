package com.example.quanlysach.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quanlysach.database.DataBaseHelper;
import com.example.quanlysach.model.hoaDon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private SQLiteDatabase sqLiteDatabase;
    private DataBaseHelper dataBaseHelper;
    public static final String TABLE_NAME="hoadon";
    public static final String SQL_HOA_DON="CREATE TABLE hoadon(mahoadon text primary key,ngaymua date);";
    public static final String  TAG="HoaDonDAO";
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

    public HoaDonDAO(Context context) {
        dataBaseHelper=new DataBaseHelper(context);
        sqLiteDatabase=dataBaseHelper.getWritableDatabase();
    }
    //Insert
    public int insertHoaDon(hoaDon hoaDon){
        ContentValues contentValues=new ContentValues();
        contentValues.put("mahoadon",hoaDon.getMaHoaDon());
        contentValues.put("ngaymua",simpleDateFormat.format(hoaDon.getNgayMua()));
        try {
            if(sqLiteDatabase.insert(TABLE_NAME,null,contentValues)==-1){
                return -1;
            }
        }
        catch (Exception e){
            Log.e(TAG,e.toString());
        }
        return 1;
    }
    //getAll
    public List<hoaDon> getAll() throws ParseException {
        List<hoaDon> hoaDonList=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            hoaDon hoaDon=new hoaDon();
            hoaDon.setMaHoaDon(cursor.getString(0));
            hoaDon.setNgayMua(simpleDateFormat.parse(cursor.getString(1)));
            hoaDonList.add(hoaDon);
            cursor.moveToNext();
        }
        cursor.close();
        return hoaDonList;
    }
    public int DeleteHoaDonById(String mahoadon){
        int result=sqLiteDatabase.delete(TABLE_NAME,"mahoadon=?",new String[]{mahoadon});
        if(result==0){
            return -1;
        }
        return 1;
    }
}
