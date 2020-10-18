package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.quanlysach.dao.NguoiDungDAO;
import com.example.quanlysach.model.NguoiDung;

public class ChangePasswordActivity extends AppCompatActivity {
EditText edPass,edRePass;
NguoiDungDAO nguoiDungDAO;
Button btnLuu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        setTitle("ĐỔI MẬT KHẨU");
        edPass=findViewById(R.id.edRePassword2);
        edRePass=findViewById(R.id.edRePassword2);
        btnLuu=findViewById(R.id.btnChangePass);

    }
    public int validateForm(){
        int check=1;
        if(edPass.getText().length()==0 || edRePass.getText().length()==0){
            Toast.makeText(getApplicationContext(),"Bạn phải nhập đủ thông tin",Toast.LENGTH_LONG).show();
            check=-1;
        }
        else if(edPass.length()<6 || edRePass.length()<6){
         Toast.makeText(getApplicationContext(),"Mật khẩu phải lớn hơn 6 kí tự",Toast.LENGTH_LONG).show();
        }
        else {
            String pass=edPass.getText().toString();
            String repass=edRePass.getText().toString();
            if(!pass.equals(repass)){
                Toast.makeText(getApplicationContext(),"Mật khẩu bạn nhập không trùng khớp",Toast.LENGTH_LONG).show();
                check=-1;
            }
        }
        return check;
    }
    public void changePassword(View view) {
        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String strUserName = preferences.getString("USERNAME", "");
        nguoiDungDAO = new NguoiDungDAO(ChangePasswordActivity.this);
        NguoiDung nguoiDung = new NguoiDung(strUserName, edPass.getText().toString(), "", "");
        try {
            if (validateForm() > 0) {
                if (nguoiDungDAO.changePasswordNguoiDung(nguoiDung) > 0) {
                    Toast.makeText(getApplicationContext(), "Lưu Thành Công", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Lưu Thất Bại", Toast.LENGTH_LONG).show();
                }
            }
            finish();
        }
        catch (Exception e){
            Log.e("ERRO",e.toString());


    }
    }

}