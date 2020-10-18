package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlysach.dao.NguoiDungDAO;
import com.example.quanlysach.model.NguoiDung;

import java.util.List;

public class UserActivity extends AppCompatActivity {
EditText edusername,edpassword,edrepassword,edphone,edhoten;
Button btnluu,btnhuy,btndanhsach;
NguoiDungDAO nguoiDungDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THÊM NGƯỜI DÙNG");
        setContentView(R.layout.activity_user);
        edusername=findViewById(R.id.edUserName1);
        edpassword=findViewById(R.id.edPassWord1);
        edrepassword=findViewById(R.id.edRePassWord);
        edphone=findViewById(R.id.edPhone);
        edhoten=findViewById(R.id.edHoTen);
        btnluu=findViewById(R.id.btnLuu);
        btnhuy=findViewById(R.id.btnHuy);
        btndanhsach=findViewById(R.id.btnShow);

    }
    public void huy(View view){
        finish();
    }
    public void showUser (View view){
        finish();
    }

    public void addUser(View view){
        nguoiDungDAO=new NguoiDungDAO(UserActivity.this);
//        for (int i=0;i<nguoiDungDAO.getNguoiDungByID().size();i++){
//            if(edusername.getText().toString().equals(nguoiDungDAO.getNguoiDungByID().get(i))==true){
//                Toast.makeText(getApplicationContext(),"Tên người dùng không được trùng",Toast.LENGTH_SHORT).show();
//            }
//        }
        NguoiDung nguoiDung=new NguoiDung(edusername.getText().toString(),edpassword.getText().toString(),edphone.getText().toString(),edhoten.getText().toString());
        try {
            if(validateForm()>0){
                if(nguoiDungDAO.insertNguoiDung(nguoiDung)>0){
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }}
        public int validateForm(){
            int check=1;
            String regex = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
            NguoiDungDAO nguoiDungDAO=new NguoiDungDAO(UserActivity.this);
            List<String> arr=nguoiDungDAO.getNguoiDungByID();
            for(int i=0;i<arr.size();i++){
                if(edusername.getText().toString().equals(arr.get(i))==true){
                    Toast.makeText(getApplicationContext(),"Tên người dùng không được trùng",Toast.LENGTH_SHORT).show();
                    check=-1;
                }
                else {
                    check=1;
                }
            }
            if (edusername.getText().length() == 0 || edhoten.getText().length() == 0
                    || edphone.getText().length() == 0 || edpassword.getText().length()==0
                    || edrepassword.getText().length() == 0) {
                Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông ", Toast.LENGTH_SHORT).show();
                check = -1;
            }
else if(edpassword.getText().length()<6 || edrepassword.getText().length()<6){
                Toast.makeText(getApplicationContext(), "Mật khẩu >6", Toast.LENGTH_SHORT).show();
                check=-1;
            }
else if(edphone.getText().toString().matches(regex)==false){
                Toast.makeText(getApplicationContext(), "Định dạng số sai", Toast.LENGTH_SHORT).show();
                check=-1;
            }
            else {
                String pass = edpassword.getText().toString();
                String rePass = edrepassword.getText().toString();
                if (!pass.equals(rePass)) {
                    Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp",
                            Toast.LENGTH_SHORT).show();
                    check = -1;
                }
            }
            return check;
        }

}




