package com.example.quanlysach.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quanlysach.database.DataBaseHelper;
import com.example.quanlysach.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {
    private SQLiteDatabase db;
    private DataBaseHelper dbHelper;
    public static final String TABLE_NAME = "NguoiDung";
    public static final String SQL_NGUOI_DUNG = "CREATE TABLE NguoiDung(username text primary key,password text,phone text,hoten text);";
    public static final String TAG = "NguoiDungDAO";

    public NguoiDungDAO(Context context) {
        dbHelper = new DataBaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public int insertNguoiDung(NguoiDung nd) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUsername());
        values.put("password", nd.getPassword());
        values.put("phone", nd.getPhone());
        values.put("hoten", nd.getHoTen());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return 1;
    }

    //getAll
    public List<NguoiDung> getAllNguoiDung() {
        List<NguoiDung> dsNguoiDung = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            NguoiDung nguoiDung = new NguoiDung();
            nguoiDung.setUsername(c.getString(0));
            nguoiDung.setPassword(c.getString(1));
            nguoiDung.setPhone(c.getString(2));
            nguoiDung.setHoTen(c.getString(3));
            dsNguoiDung.add(nguoiDung);
            Log.d("//=====", nguoiDung.toString());
            c.moveToNext();
        }
        c.close();
        return dsNguoiDung;
    }

    //update
    public int updateNguoiDung(NguoiDung nguoiDung) {
        ContentValues values = new ContentValues();
        values.put("username", nguoiDung.getUsername());
        values.put("password", nguoiDung.getPassword());
        values.put("phone", nguoiDung.getPhone());
        values.put("hoten", nguoiDung.getHoTen());
        int result = db.update(TABLE_NAME, values, "username=?", new String[]{nguoiDung.getUsername()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //delete
    public int deleteNguoiDungByID(String username){
        int result = db.delete(TABLE_NAME,"username=?",new String[]{username});
        if (result == 0)
            return -1;
        return 1;
    }
    //check login
    public int checkLogin(String username, String password) {

        int result = 0;
        Cursor cursor = db.rawQuery("select *from NguoiDung where username=? and password=?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return result = 1;
        } else {
           return result = -1;
        }


    }
    public int changePasswordNguoiDung(NguoiDung nguoiDung){
        ContentValues values=new ContentValues();
        values.put("username",nguoiDung.getUsername());
        values.put("password",nguoiDung.getPassword());
        int result=db.update(TABLE_NAME,values,"username=?",new String[]{nguoiDung.getUsername()});
        if(result==0){
            return -1;
        }
        return 1;
    }
    public List<String> getNguoiDungByID(){
        List<String> nguoiDungList=new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,new String[]{"username"},null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            String tennguoidung=c.getString(0);
            nguoiDungList.add(tennguoidung);
            c.moveToNext();
        }
        c.close();
        return nguoiDungList;
    }
}







