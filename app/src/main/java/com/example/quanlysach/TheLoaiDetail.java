package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlysach.dao.TheLoaiDAO;
import com.example.quanlysach.model.TheLoai;

public class TheLoaiDetail extends AppCompatActivity {
    TextView tvMaTheLoai;
    EditText edTenTheLoai,edViTri,edMoTa;
    Button btnSua,btnHuy;
    TheLoaiDAO theLoaiDAO;
    String maTheLoai,tenTheLoai,moTa;
    int vitri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai_detail);
        tvMaTheLoai=findViewById(R.id.tvMaTheLoaiDetail);
        edTenTheLoai=findViewById(R.id.edTenTheLoai1);
        edViTri=findViewById(R.id.edViTri1);
        edMoTa=findViewById(R.id.edMoTa1);
        btnHuy=findViewById(R.id.btnHuy);
       btnSua=findViewById(R.id.btnsua);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
       maTheLoai=bundle.getString("matheloai");
        tenTheLoai=bundle.getString("tentheloai");
        moTa=bundle.getString("mota");
         vitri=bundle.getInt("vitri");
        edTenTheLoai.setText(tenTheLoai);
        tvMaTheLoai.setText(maTheLoai);
        edMoTa.setText(moTa);
        edViTri.setText(String.valueOf(vitri));
    }
    public void sua(View view){
        theLoaiDAO=new TheLoaiDAO(TheLoaiDetail.this);
        TheLoai theLoai=new TheLoai(maTheLoai,edTenTheLoai.getText().toString(),edMoTa.getText().toString(),Integer.parseInt(edViTri.getText().toString()));
        if(theLoaiDAO.updateTheLoai(theLoai)==1){
            Toast.makeText(getApplicationContext(),"Sửa Thành Công",Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(),"Sửa Không Thành Công",Toast.LENGTH_SHORT).show();
        }
    }
    public void huy(View view){
        finish();
    }
}