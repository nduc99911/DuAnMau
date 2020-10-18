package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlysach.adapter.hoaDonAdapter;
import com.example.quanlysach.adapter.hoaDonChiTietAdapter;
import com.example.quanlysach.dao.HoaDonChiTietDAO;
import com.example.quanlysach.dao.SachDAO;
import com.example.quanlysach.model.HoaDonChiTiet;
import com.example.quanlysach.model.Sach;
import com.example.quanlysach.model.hoaDon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonChiTietActivity extends AppCompatActivity {
EditText edMaHoaDon,edMaSach,edSoLuongMua;
TextView tvThanhTien;
Button btnThemHoaDOn,btnthanhtoan;
ListView listView;
String mahoadon;
SachDAO sachDAO;
com.example.quanlysach.adapter.hoaDonChiTietAdapter hoaDonChiTietAdapter;
List<HoaDonChiTiet> LitsHDCT=new ArrayList<>();
HoaDonChiTietDAO hoaDonChiTietDAO;
double thanhtien=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        edMaHoaDon=findViewById(R.id.edMaHoaDon);
        edMaSach=findViewById(R.id.edMaSach);
        edSoLuongMua=findViewById(R.id.edSoLuongMua);
        btnThemHoaDOn=findViewById(R.id.btnThem);
        btnthanhtoan=findViewById(R.id.btnThanhToan);
        listView=findViewById(R.id.lvCart);
        tvThanhTien=findViewById(R.id.tvThanhTien);
        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        mahoadon=bundle.getString("mahoadon");
        edMaHoaDon.setText(mahoadon);
        hoaDonChiTietAdapter=new hoaDonChiTietAdapter(HoaDonChiTietActivity.this,LitsHDCT);
        listView.setAdapter(hoaDonChiTietAdapter);

}
public void ADDHoaDonCHITIET(View view){
        if(validation()<0){
            Toast.makeText(getApplicationContext(),"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
        }
        else {
            hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonChiTietActivity.this);
            sachDAO=new SachDAO(HoaDonChiTietActivity.this);
            Sach sach=sachDAO.getSachByID(edMaSach.getText().toString());
            if(sach!=null){
                int pos=checkMaSach1(LitsHDCT,edMaSach.getText().toString());
                hoaDon hoaDon=new hoaDon(edMaHoaDon.getText().toString(),new Date());
                HoaDonChiTiet hoaDonChiTiet=new HoaDonChiTiet(1,hoaDon,sach,Integer.parseInt(edSoLuongMua.getText().toString()));
                if(pos>=0){
                    int soluong=LitsHDCT.get(pos).getSoLuongMua();
                    hoaDonChiTiet.setSoLuongMua(soluong+Integer.parseInt(edSoLuongMua.getText().toString()));
                    LitsHDCT.set(pos,hoaDonChiTiet);

                }
                else {
                    LitsHDCT.add(hoaDonChiTiet);
                }
                hoaDonChiTietAdapter.changeDataset(LitsHDCT);
            }else {
                Toast.makeText(getApplicationContext(),"Mã sách không tồn tại",Toast.LENGTH_SHORT).show();
            }
        }

}
public void thanhToanHoaDon(View view){
        hoaDonChiTietDAO=new HoaDonChiTietDAO(HoaDonChiTietActivity.this);
        thanhtien=0;
        try {
             for(HoaDonChiTiet hd:LitsHDCT){
                 hoaDonChiTietDAO.inserHoaDonChiTiet(hd);
                 thanhtien=thanhtien+hd.getSoLuongMua()*hd.getSach().getGiaBia();
             }
             tvThanhTien.setText("Tông tiền"+thanhtien);
        }
        catch (Exception e){

        }

}

    public int validation(){
        if
        (edMaSach.getText().toString().isEmpty()||edSoLuongMua.getText().toString().isEmpty()||
                edMaHoaDon.getText().toString().isEmpty()){
            return -1;
        }
        return 1;
    }
    public int checkMaSach1(List<HoaDonChiTiet> hoaDonChiTiets,String masach){
        int pos=-1;
        for (int i=0;i<hoaDonChiTiets.size();i++){
            HoaDonChiTiet hoaDonChiTiet=hoaDonChiTiets.get(i);
            if(hoaDonChiTiet.getSach().getMaSach().equals(masach)){
                pos=i;
                break;
            }

        }
        return pos;
    }
}