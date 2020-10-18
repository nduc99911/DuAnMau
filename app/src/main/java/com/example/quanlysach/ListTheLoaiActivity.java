package com.example.quanlysach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.quanlysach.adapter.theLoaiAdapter;
import com.example.quanlysach.dao.TheLoaiDAO;
import com.example.quanlysach.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class ListTheLoaiActivity extends AppCompatActivity {
public static List<TheLoai> theLoaiList=new ArrayList<>();
ListView listView;
theLoaiAdapter theLoaiAdapter=null;
TheLoaiDAO theLoaiDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THỂ LOẠI");
        setContentView(R.layout.activity_list_the_loai);
        theLoaiDAO =new TheLoaiDAO(this);
        listView=(ListView)findViewById(R.id.lvTheLoai);
        registerForContextMenu(listView);
        theLoaiList=theLoaiDAO.getAllTheLoai();
        theLoaiAdapter=new theLoaiAdapter(this,theLoaiList);
        listView.setAdapter(theLoaiAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ListTheLoaiActivity.this,TheLoaiDetail.class);
                Bundle bundle=new Bundle();
                bundle.putString("matheloai",theLoaiList.get(position).getMaTheLoai());
                bundle.putString("tentheloai",theLoaiList.get(position).getTenTheLoai());
                bundle.putInt("vitri",theLoaiList.get(position).getViTri());
                bundle.putString("mota",theLoaiList.get(position).getMoTa());
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_the_loai,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        if(item.getItemId()==R.id.ThemTheLoai){
            intent =new Intent(ListTheLoaiActivity.this,TheLoaiActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onResume() {
        super.onResume();
        theLoaiList.clear();
        theLoaiList = theLoaiDAO.getAllTheLoai();
        theLoaiAdapter.changeDataset(theLoaiList);
    }

}