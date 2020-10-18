package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlysach.dao.TheLoaiDAO;
import com.example.quanlysach.model.TheLoai;

import java.util.List;

public class TheLoaiActivity extends AppCompatActivity {
EditText edMaTheLoai,edTenTheLoai,edViTri,edMoTa;
Button btnLuu,btnHuy,btnShow;
TheLoaiDAO theLoaiDAO;
String maTheLoai,tenTheLoai,moTa;
int vitri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THÊM THỂ LOẠI");
        setContentView(R.layout.activity_the_loai);
        edMaTheLoai=findViewById(R.id.edMaTheLoai);
        edTenTheLoai=findViewById(R.id.edTenTheLoai1);
        edViTri=findViewById(R.id.edViTri1);
        edMoTa=findViewById(R.id.edMoTa1);
        btnHuy=findViewById(R.id.btnHuy);
        btnLuu=findViewById(R.id.btnLuu);
        btnShow=findViewById(R.id.btnShow);


    }

    public void LuuTheLoai(View view) {
        theLoaiDAO=new TheLoaiDAO(TheLoaiActivity.this);
if(ValidateForm()>0){
    TheLoai theLoai=new TheLoai(edMaTheLoai.getText().toString(),edTenTheLoai.getText().toString(),edMoTa.getText().toString(),Integer.parseInt(edViTri.getText().toString()));
    if(theLoaiDAO.inserTheLoai(theLoai)>0){
        Toast.makeText(getApplicationContext(),"Insert Thành Công",Toast.LENGTH_SHORT).show();
    }
    else {
        Toast.makeText(getApplicationContext(),"Insert Khoong Thành Công",Toast.LENGTH_SHORT).show();
    }
}
//        try {
//            for(int i=0;i<arr.size();i++){
//                if(edMaTheLoai.getText().toString().equals(arr.get(i))==true){
//                Toast.makeText(getApplicationContext(),"Mã thể loại không được trùng",Toast.LENGTH_LONG).show();
//            }
//        }
//            maTheLoai=edMaTheLoai.getText().toString();
//            tenTheLoai=edTenTheLoai.getText().toString();
//            moTa=edMoTa.getText().toString();
//            vitri= Integer.parseInt(edViTri.getText().toString());
//            TheLoai theLoai=new TheLoai(maTheLoai,tenTheLoai,moTa,vitri);
//            if(maTheLoai.equals("") || tenTheLoai.equals("") || moTa.equals("") || edViTri.getText().toString().equals("")){
//                Toast.makeText(getApplicationContext(),"Không được bỏ trống đâu nha",Toast.LENGTH_LONG).show();
//            }
//             else if (theLoaiDAO.inserTheLoai(theLoai)>0) {
//                    Toast.makeText(getApplicationContext(), "Thêm Thành Công", Toast.LENGTH_LONG).show();
//                    finish();
//                }
//                 else {
//                    Toast.makeText(getApplicationContext(), "Thêm Thất Bại", Toast.LENGTH_LONG).show();
//                }
//        }
//        catch (Exception e)
//        {
//            Log.e("ERRO",e.toString());
//            Toast.makeText(getApplicationContext(),"Vị trí phải là 1 số nha",Toast.LENGTH_LONG).show();
//        }
    }


    public void HuyTheLoai(View view){
        finish();
    }
    public void ShowTheLoai(View view){
        finish();
    }
    public int ValidateForm(){
       int check=1;
        theLoaiDAO=new TheLoaiDAO(TheLoaiActivity.this);
        if(edMaTheLoai.getText().toString().length()==0 || edTenTheLoai.getText().toString().length()==0 || edMoTa.getText().toString().length()==0 || edViTri.getText().toString().length()==0){
            Toast.makeText(getApplicationContext(),"Bạn phải nhập đủ thông tin",Toast.LENGTH_LONG).show();
            check=-1;
        }
        try{
            int so = Integer.parseInt(edViTri.getText().toString());
            check=1;
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), "Không đúng định dạng số", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }

}