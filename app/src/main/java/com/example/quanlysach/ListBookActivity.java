package com.example.quanlysach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.quanlysach.adapter.sachAdapter;
import com.example.quanlysach.dao.SachDAO;
import com.example.quanlysach.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class ListBookActivity extends AppCompatActivity {
    public static List<Sach> sachList=new ArrayList<>();
sachAdapter sachAdapter=null;
ListView listView;
SachDAO sachDAO;
EditText edBook;
Button btnTim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_book);
        setTitle("QUẢN LÝ SÁCH");
        listView=findViewById(R.id.lvBook);
        edBook=findViewById(R.id.edSearchBook);
        btnTim=findViewById(R.id.btnTim);
        sachDAO=new SachDAO(ListBookActivity.this);
        sachList=sachDAO.getAllSach();
        sachAdapter=new sachAdapter(this,sachList);
        listView.setAdapter(sachAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sach sach = (Sach) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListBookActivity.this,SachActivity.class);
                Bundle b = new Bundle();
                b.putString("masach", sach.getMaSach());
                b.putString("matheloai", sach.getMaTheLoai());
                b.putString("tensach", sach.getTenSach());
                b.putString("tacgia", sach.getTacGia());
                b.putString("nhaxuatban", sach.getNhaXuatBan());
                b.putString("giabia", String.valueOf(sach.getGiaBia()));
                b.putString("soluong", String.valueOf(sach.getSoLuong()));
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        listView.setTextFilterEnabled(true);
        edBook.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count < before) {
                    sachAdapter.resetData();
                }
                sachAdapter.getFilter().filter(s.toString());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sachList.clear();
        sachList = sachDAO.getAllSach();
        sachAdapter.changeDataSet(sachList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sach,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.ThemBook:
                Intent intent=new Intent(ListBookActivity.this,SachActivity.class);
                startActivity(intent);
                return (true);
        }
        return super.onOptionsItemSelected(item);
    }
}