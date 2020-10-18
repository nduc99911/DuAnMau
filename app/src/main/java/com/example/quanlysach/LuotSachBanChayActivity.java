package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quanlysach.adapter.sachAdapter;
import com.example.quanlysach.dao.SachDAO;
import com.example.quanlysach.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class LuotSachBanChayActivity extends AppCompatActivity {
EditText edThang;
Button button;
ListView listView;
SachDAO sachDAO;
List<Sach> dsSachh=new ArrayList<>();
com.example.quanlysach.adapter.sachAdapter sachAdapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("TOP 10 SÁCH BÁN CHẠY");
        setContentView(R.layout.activity_luot_sach_ban_chay);
        edThang=findViewById(R.id.edThang);
        listView=findViewById(R.id.lvBookTop);

    }
    public void VIEW_SACH_TOP_10(View view) {
        if (Integer.parseInt(edThang.getText().toString()) > 13 ||
                Integer.parseInt(edThang.getText().toString()) < 0) {
            Toast.makeText(getApplicationContext(), "Không đúng định dạng tháng (1- 12)", Toast.LENGTH_SHORT).show();
        } else {
            sachDAO = new SachDAO(LuotSachBanChayActivity.this);
            dsSachh = sachDAO.getTop10Sach(edThang.getText().toString());
            sachAdapter = new sachAdapter(LuotSachBanChayActivity.this, dsSachh);
            listView.setAdapter(sachAdapter);
        }
    }
}