package com.example.quanlysach.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quanlysach.database.DataBaseHelper;
import com.example.quanlysach.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private SQLiteDatabase sqLiteDatabase;
    private DataBaseHelper dataBaseHelper;
    public static final String TABLE_NAME="Sach";
    public static final String SQL_SACH="CREATE TABLE Sach(masach text primary key,matheloai text,tensach text,tacgia text,nhaxuatban text,giabia double,soluong int);";
    public static final String TAG="SachDAO";

    public SachDAO(Context context) {
        dataBaseHelper=new DataBaseHelper(context);
        sqLiteDatabase=dataBaseHelper.getWritableDatabase();
    }
    //insert
    public int InsertSach(Sach sach){
        ContentValues values=new ContentValues();
        values.put("masach",sach.getMaSach());
        values.put("matheloai",sach.getMaTheLoai());
        values.put("tensach",sach.getTenSach());
        values.put("tacgia",sach.getMaTheLoai());
        values.put("nhaxuatban",sach.getNhaXuatBan());
        values.put("giabia",sach.getGiaBia());
        values.put("soluong",sach.getSoLuong());
        if (checkPrimaryKey(sach.getMaSach())){
            int result = sqLiteDatabase.update(TABLE_NAME,values,"masach=?", new
                    String[]{sach.getMaSach()});
            if (result == 0){
                return -1;
            }
        }else {
            try {
                if (sqLiteDatabase.insert(TABLE_NAME, null, values) == -1) {
                    return -1;
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }
        }
        return 1;
    }
    public boolean checkPrimaryKey(String strPrimaryKey){
        //SELECT
        String[] columns = {"masach"};
        //WHERE clause
        String selection = "masach=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try{
            c = sqLiteDatabase.query(TABLE_NAME, columns, selection, selectionArgs, null, null,
                    null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if(i <= 0){
                return false;
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public List<Sach> getAllSach(){
        List<Sach> sachList=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            Sach sach=new Sach();
            sach.setMaSach(cursor.getString(0));
            sach.setMaTheLoai(cursor.getString(1));
            sach.setTenSach(cursor.getString(2));
            sach.setTacGia(cursor.getString(3));
            sach.setNhaXuatBan(cursor.getString(4));
            sach.setGiaBia(cursor.getDouble(5));
            sach.setSoLuong(cursor.getInt(6));
            sachList.add(sach);
            cursor.moveToNext();
        }
        cursor.close();
        return sachList;


    }
    public Sach getSachByID(String masach){
        Sach sach=null;
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME,null,"masach=?",new String[]{masach},null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            sach=new Sach();
            sach.setMaSach(cursor.getString(0));
            sach.setMaTheLoai(cursor.getString(1));
            sach.setTenSach(cursor.getString(2));
            sach.setTacGia(cursor.getString(3));
            sach.setNhaXuatBan(cursor.getString(4));
            sach.setGiaBia(cursor.getDouble(5));
            sach.setSoLuong(cursor.getInt(6));
            break;
        }
        cursor.close();
        return sach;
    }
    public List<String> checkTrungTen(){
        List<String> sachList=new ArrayList<>();
        Cursor c = sqLiteDatabase.query(TABLE_NAME,new String[]{"masach"},null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            String masach=c.getString(0);
            sachList.add(masach);
            c.moveToNext();
        }
        c.close();
        return sachList;
    }

    public int updateSach(Sach sach){
        ContentValues values=new ContentValues();
        values.put("masach",sach.getMaSach());
        values.put("matheloai",sach.getMaTheLoai());
        values.put("tensach",sach.getTenSach());
        values.put("tacgia",sach.getTacGia());
        values.put("nhaxuatban",sach.getNhaXuatBan());
        values.put("giabia",sach.getGiaBia());
        values.put("soluong",sach.getSoLuong());
        int result=sqLiteDatabase.update(TABLE_NAME,values,"masach=?",new String[]{sach.getMaSach()});
        if(result==0){
            return -1;
        }
        return 1;
    }
    public int deleteSachByMaSach(String maSach){
        int result=sqLiteDatabase.delete(TABLE_NAME,"masach=?",new  String[]{maSach});
        if (result == 0)
            return -1;
        return 1;
    }
    public List<Sach> getTop10Sach(String month){
        List<Sach> dsSach=new ArrayList<>();
        if(Integer.parseInt(month)<10){
            month="0"+month;
        }
        String SQL=" SELECT masach, SUM(soluong) as soluong from HoaDonChiTiet INNER JOIN hoadon "+
                " ON hoadon.mahoadon = HoaDonChiTiet.mahoadon WHERE " +
                " strftime( '%m' ,hoadon.ngaymua)= '"+month+"' "+
                " GROUP BY masach ORDER BY soluong DESC LIMIT 10 ";
        Cursor cursor=sqLiteDatabase.rawQuery(SQL,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            Sach sach=new Sach();
            sach.setMaSach(cursor.getString(0));
            sach.setSoLuong(cursor.getInt(1));
            sach.setGiaBia((double) 0);
            sach.setMaTheLoai("");
            sach.setTenSach("");
            sach.setTacGia("");
            sach.setNhaXuatBan("");
            dsSach.add(sach);
            cursor.moveToNext();
        }
        cursor.close();
        return dsSach;
    }



}
