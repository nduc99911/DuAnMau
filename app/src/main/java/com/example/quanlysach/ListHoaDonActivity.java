package com.example.quanlysach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.quanlysach.adapter.hoaDonAdapter;
import com.example.quanlysach.adapter.sachAdapter;
import com.example.quanlysach.dao.HoaDonDAO;
import com.example.quanlysach.model.hoaDon;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ListHoaDonActivity extends AppCompatActivity {
ListView listView;
EditText editText;
HoaDonDAO hoaDonDAO;
List<hoaDon> hoaDonList=new ArrayList<>();
hoaDonAdapter hoaDonAdapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoa_don);
        listView=findViewById(R.id.lvHoaDon);
        setTitle("HOÁ ĐƠN");
        editText=findViewById(R.id.edSearch);
        hoaDonDAO=new HoaDonDAO(ListHoaDonActivity.this);
        try {
            hoaDonList=hoaDonDAO.getAll();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        hoaDonAdapter=new hoaDonAdapter(ListHoaDonActivity.this,hoaDonList);
        listView.setAdapter(hoaDonAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                hoaDon hoaDon = (hoaDon) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListHoaDonActivity.this,
                        ListHoaDonChiTietActivity.class);
                Bundle b = new Bundle();
                b.putString("MAHOADON", hoaDon.getMaHoaDon());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        listView.setTextFilterEnabled(true);
       editText.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(count<before){
                hoaDonAdapter.ResetData();
            }
            hoaDonAdapter.getFilter().filter(s.toString());

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hoa_don,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.ThemHoaDon:
                intent=new Intent(ListHoaDonActivity.this,HoaDonActivity.class);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hoaDonList.clear();
        try {
            hoaDonList = hoaDonDAO.getAll();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        hoaDonAdapter.changeDataSet(hoaDonList);
    }


}