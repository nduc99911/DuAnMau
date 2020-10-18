package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.quanlysach.dao.NguoiDungDAO;
import com.example.quanlysach.model.NguoiDung;

public class ActivityNguoiDungDetail extends AppCompatActivity {
EditText edphone,edHoVaTen;
Button btnSua,btnHuy;
NguoiDungDAO nguoiDungDAO;
String username,fullname,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("CHI TIẾT NGƯỜI DÙNG");
        setContentView(R.layout.activity_nguoi_dung_detail);
        edphone=findViewById(R.id.edPhone);
        edHoVaTen=findViewById(R.id.edFullName);
        btnHuy=findViewById(R.id.btnCancel);
        btnSua=findViewById(R.id.btnUpdateUser);
        nguoiDungDAO=new NguoiDungDAO(ActivityNguoiDungDetail.this);
        Intent  intent=getIntent();
        Bundle bundle=intent.getExtras();
        username=bundle.getString("USERNAME");
        fullname=bundle.getString("FULLNAME");
        phone=bundle.getString("PHONE");
        edphone.setText(phone);
        edHoVaTen.setText(fullname);

    }
    public void Huy(View view){
        finish();
    }
    public void updateUser(View view){
        NguoiDung nguoiDung=new NguoiDung(username,"",edphone.getText().toString(),edHoVaTen.getText().toString());
        if(nguoiDungDAO.updateNguoiDung(nguoiDung)>0){
            Toast.makeText(getApplicationContext(),"Update thành công!",Toast.LENGTH_LONG).show();
        }

    }
}