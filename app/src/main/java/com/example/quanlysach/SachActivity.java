package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlysach.dao.SachDAO;
import com.example.quanlysach.dao.TheLoaiDAO;
import com.example.quanlysach.model.Sach;
import com.example.quanlysach.model.TheLoai;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

public class SachActivity extends AppCompatActivity {
    EditText edmaSach, edtenSach, edtacGia, ednhaXuatBan, edgiaBia, edsoLuong;
    Spinner spinner;
    Button btnLuu, btnHuy, btnShow;
    SachDAO sachDAO;
    TheLoaiDAO theLoaiDAO;
    List<String> theLoaiList = new ArrayList<>();
    String matheloai = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THÊM SÁCH");
        setContentView(R.layout.activity_sach);
        edmaSach = findViewById(R.id.edMaSach);
        edtenSach = findViewById(R.id.edTenSach);
        edtacGia = findViewById(R.id.edTacGia);
        ednhaXuatBan = findViewById(R.id.edNXB);
        edgiaBia = findViewById(R.id.edGiaBia);
        edsoLuong = findViewById(R.id.edSoLuong);
        spinner = findViewById(R.id.spnTheLoai);
        btnLuu = findViewById(R.id.btnAddBook);
        btnHuy = findViewById(R.id.btnCancelBook);
        btnShow = findViewById(R.id.btnShowBook);

        getTheLoai();
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edmaSach.setText(b.getString("masach"));
            String maTheLoai = b.getString("matheloai");
            edtenSach.setText(b.getString("tensach"));
            ednhaXuatBan.setText(b.getString("nhaxuatban"));
            edtacGia.setText(b.getString("tacgia"));
            edgiaBia.setText(b.getString("giabia"));
            edsoLuong.setText(b.getString("soluong"));
            spinner.setSelection(checkPositionTheLoai(maTheLoai));
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                matheloai = theLoaiList.get(spinner.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void addBook(View view) {
        sachDAO = new SachDAO(SachActivity.this);
        List<String> arr = sachDAO.checkTrungTen();
        for (int i = 0; i < arr.size(); i++) {
            if (edmaSach.getText().toString().equals(arr.get(i)) == true) {
                Toast.makeText(getApplicationContext(), "Mã Sách không được trùng", Toast.LENGTH_SHORT).show();
            }
        }
        try {
            Sach sach = new Sach(edmaSach.getText().toString(), matheloai, edtacGia.getText().toString(), edtenSach.getText().toString(), ednhaXuatBan.getText().toString(), Double.parseDouble(edgiaBia.getText().toString()), Integer.parseInt(edsoLuong.getText().toString()));
            if (edmaSach.getText().toString().isEmpty() || edtenSach.getText().toString().isEmpty() || edtacGia.getText().toString().isEmpty() || ednhaXuatBan.getText().toString().isEmpty() || edsoLuong.getText().toString().isEmpty() || edgiaBia.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Không được để trống!", Toast.LENGTH_SHORT).show();
            } else if (edmaSach.getText().toString().length() < 5) {
                Toast.makeText(getApplicationContext(), "Mã sách>5", Toast.LENGTH_SHORT).show();
            }else if(Double.parseDouble(edgiaBia.getText().toString())==0 || Integer.parseInt(edsoLuong.getText().toString())==0){
                Toast.makeText(getApplicationContext(), "Giá hoặc số lượng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
            }
            else if (sachDAO.InsertSach(sach) > 0) {
                Toast.makeText(getApplicationContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "2 Dòng Cuối Phải Là 1 số !", Toast.LENGTH_SHORT).show();
        }


    }

    public void showBook(View view) {
        finish();
    }

    public void CancelBook(View view) {
        finish();
    }

    public void getTheLoai() {
        theLoaiDAO = new TheLoaiDAO(SachActivity.this);
        theLoaiList = theLoaiDAO.getTenTheLoai();
        ArrayAdapter<String> theLoaiArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, theLoaiList);
        theLoaiArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(theLoaiArrayAdapter);
    }


    public int checkPositionTheLoai(String strTheLoai) {
        List<TheLoai> theLoais = new ArrayList<>();
        for (int i = 0; i < theLoais.size(); i++) {
            if (strTheLoai.equals(theLoais.get(i).getMaTheLoai())) {
                return i;
            }
        }
        return 0;
    }
}