package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlysach.dao.HoaDonDAO;
import com.example.quanlysach.model.HoaDonChiTiet;
import com.example.quanlysach.model.hoaDon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class HoaDonActivity extends AppCompatActivity {
EditText edMaHoaDon,edNgay;
Button button;
 SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
 HoaDonDAO hoaDonDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        edMaHoaDon=findViewById(R.id.edMaHoaDon);
        edNgay=findViewById(R.id.edNgayMua);
        button=findViewById(R.id.picDate);
        final Calendar calendar=Calendar.getInstance();
        final int year= calendar.get(calendar.YEAR);
        final int month= calendar.get(calendar.MONTH);
        final int day= calendar.get(calendar.DAY_OF_MONTH);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(HoaDonActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar calendar=new GregorianCalendar(year,month,dayOfMonth);
                        setDate(calendar);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
    }
    public void setDate(final Calendar calendar){
        edNgay.setText(simpleDateFormat.format(calendar.getTime()));
    }



public void ADDHoaDon(View view) throws ParseException {
    if(edMaHoaDon.getText().toString().length()==0 || edNgay.getText().toString().length()==0){
        Toast.makeText(getApplicationContext(),"Không được bỏ trống đâu nha!",Toast.LENGTH_SHORT).show();
    }
        try {
   hoaDonDAO=new HoaDonDAO(HoaDonActivity.this);
    String mahoadon=edMaHoaDon.getText().toString();
    hoaDon hoaDon=new hoaDon(mahoadon,simpleDateFormat.parse(edNgay.getText().toString()));

    if(hoaDonDAO.insertHoaDon(hoaDon)>0){
        Toast.makeText(getApplicationContext(),"Insert Thành Công",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(HoaDonActivity.this, HoaDonChiTietActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("mahoadon",mahoadon);
        intent.putExtras(bundle);
        startActivity(intent);

    }
    else {
        Toast.makeText(getApplicationContext(),"Insert không Thành Công",Toast.LENGTH_SHORT).show();

    }
        }catch (Exception e){
            Log.e("ERRO",e.toString());
        }


}





}