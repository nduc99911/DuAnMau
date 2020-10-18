package com.example.quanlysach.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlysach.R;
import com.example.quanlysach.dao.HoaDonChiTietDAO;
import com.example.quanlysach.model.HoaDonChiTiet;

import java.util.List;

public class hoaDonChiTietAdapter extends BaseAdapter {
    Activity context;
    List<HoaDonChiTiet> listHoaDonChiTiet;
    LayoutInflater inflater;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    public hoaDonChiTietAdapter(Activity context, List<HoaDonChiTiet> listHoaDonChiTiet) {
        super();
        this.context = context;
        this.listHoaDonChiTiet = listHoaDonChiTiet;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
    }

    @Override
    public int getCount() {
        return listHoaDonChiTiet.size();
    }

    @Override
    public Object getItem(int position) {
        return listHoaDonChiTiet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class viewHolder{
        TextView tvMaSach,tvSoLuong,tvGiaBia,tvThanhTien;
        ImageView imgDelete;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if(convertView==null){
            holder=new viewHolder();
            convertView=inflater.inflate(R.layout.item_cart,null);
            holder.imgDelete=convertView.findViewById(R.id.ivDelete);
            holder.tvGiaBia=convertView.findViewById(R.id.tvGiaBia);
            holder.tvMaSach=convertView.findViewById(R.id.tvMaSach);
            holder.tvSoLuong=convertView.findViewById(R.id.tvSoLuong);
            holder.tvThanhTien=convertView.findViewById(R.id.tvThanhTien);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hoaDonChiTietDAO.DeleteHDCTById(String.valueOf(listHoaDonChiTiet.get(position).getMaHDCT()));
                    listHoaDonChiTiet.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);

        }
        else {
            holder= (viewHolder) convertView.getTag();

        }
        HoaDonChiTiet hoaDonChiTiet=listHoaDonChiTiet.get(position);
        holder.tvMaSach.setText("Ma Sach:"+hoaDonChiTiet.getSach().getMaSach());
        holder.tvThanhTien.setText("Thành Tiền:"+hoaDonChiTiet.getSoLuongMua()*hoaDonChiTiet.getSach().getGiaBia()+"VND");
        holder.tvSoLuong.setText("So Luong:"+hoaDonChiTiet.getSoLuongMua());
        holder.tvGiaBia.setText(("Giá bìa: "+hoaDonChiTiet.getSach().getGiaBia()+" vnd"));
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<HoaDonChiTiet> item){
        this.listHoaDonChiTiet=item;
        notifyDataSetChanged();
    }
}
