package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlysach.dao.NguoiDungDAO;

public class LoginActivity extends AppCompatActivity {
EditText edUserName,edPassWord;
Button btnDangNhapButton,btnHuy;
CheckBox checkBoxRememberPass;
String strUser,strPass;
NguoiDungDAO nguoiDungDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Đăng Nhập");
        edUserName=findViewById(R.id.edUserName1);
        edPassWord=findViewById(R.id.edPassWord1);
        btnDangNhapButton=findViewById(R.id.btnDangNhap1);
        btnHuy=findViewById(R.id.btnHuy);
        checkBoxRememberPass=findViewById(R.id.chkLuuMk);
        nguoiDungDAO=new NguoiDungDAO(LoginActivity.this);

            loadRememberUser(strUser, strPass);

    }
    public void LoginUser(View view){
        strUser=edUserName.getText().toString();
        strPass=edPassWord.getText().toString();
        if(strUser.isEmpty()||strPass.isEmpty()){
                Toast.makeText(getApplicationContext(),"Tên đăng nhập và mật khẩu không được bỏ trống",
                        Toast.LENGTH_SHORT).show();
        }else {
            if (nguoiDungDAO.checkLogin(strUser,strPass)>0){
                Toast.makeText(getApplicationContext(),"Login thanh cong",Toast.LENGTH_SHORT).show();

                    rememberUser(strUser,strPass,checkBoxRememberPass.isChecked());
//                rememberUsernoCheck(strUser,strPass);
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
            }
            else if (strUser.equalsIgnoreCase("admin")&&strPass.equalsIgnoreCase("admin")){
                Toast.makeText(getApplicationContext(),"Login thanh cong",Toast.LENGTH_SHORT).show();
                rememberUser(strUser,strPass,checkBoxRememberPass.isChecked());
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
            }
            else  {
                Toast.makeText(getApplicationContext(),"Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }

    }


    }

    public void rememberUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (status==false){
            //xoa tinh trang luu tru truoc do
            edit.clear();
        }else {
            //luu du lieu
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        //luu lai toan bo
        edit.commit();
    }
    public void loadRememberUser(String strUser, String strPass){
        SharedPreferences preferences=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String user=preferences.getString("USERNAME",strUser);
        String pass=preferences.getString("PASSWORD",strPass);
    edUserName.setText(user);
edPassWord.setText(pass);
    }

}
