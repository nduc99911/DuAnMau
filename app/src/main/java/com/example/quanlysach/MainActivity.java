package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
ImageView imgUser,imgTheLoai,imgBook,imgHoaDon,imgTop,imgThongKe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgUser=findViewById(R.id.imgNguoiDung);
        imgTheLoai=findViewById(R.id.imgTheLoai);
        imgBook=findViewById(R.id.imgBook);
        imgHoaDon=findViewById(R.id.imgHoaDon);
        imgTop=findViewById(R.id.imgTop);
        imgThongKe=findViewById(R.id.imgThongKe);
        imgTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LuotSachBanChayActivity.class);
                startActivity(intent);
            }
        });
        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ListNguoiDungActivity.class);
                startActivity(intent);
            }
        });
        imgTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ListTheLoaiActivity.class);
                startActivity(intent);
            }
        });
        imgBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ListBookActivity.class);
                startActivity(intent);
            }
        });
        imgHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ListHoaDonActivity.class);
                startActivity(intent);
            }
        });
        imgThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ThongKeDoanhThu.class);
                startActivity(intent);
            }
        });
    }
}