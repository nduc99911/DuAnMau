package com.example.quanlysach.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlysach.R;
import com.example.quanlysach.dao.NguoiDungDAO;
import com.example.quanlysach.model.NguoiDung;

import java.util.List;

public class nguoiDungAdapter extends BaseAdapter {
    List<NguoiDung> arrNguoiDung;
    public Activity content;
    public LayoutInflater inflater;
    NguoiDungDAO nguoiDungDAO;

    public nguoiDungAdapter( Activity content,List<NguoiDung> arrNguoiDung) {
        super();
        this.content = content;
        this.arrNguoiDung = arrNguoiDung;
        this.inflater = (LayoutInflater)content.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nguoiDungDAO=new NguoiDungDAO(content);

    }

    @Override
    public int getCount() {
        return arrNguoiDung.size();
    }

    @Override
    public Object getItem(int position) {
        return arrNguoiDung.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class viewHolder{
        ImageView img;
        TextView txtName;
        TextView txtPhone;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if(convertView==null){
            holder=new viewHolder();
            convertView=inflater.inflate(R.layout.item_nguoi_dung,null);
            holder.img=(ImageView)convertView.findViewById(R.id.ivIcon);
            holder.txtName=(TextView)convertView.findViewById(R.id.tvName);
            holder.txtPhone=(TextView)convertView.findViewById(R.id.tvPhone);
            holder.imgDelete=(ImageView)convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nguoiDungDAO.deleteNguoiDungByID(arrNguoiDung.get(position).getUsername());
                    arrNguoiDung.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        }
        else {
            holder=(viewHolder)convertView.getTag();


        }
        NguoiDung nguoiDung=(NguoiDung) arrNguoiDung.get(position);
        if(position%3==0){
            holder.img.setImageResource(R.drawable.emone);
        }
        else if (position % 3 == 1){
            holder.img.setImageResource(R.drawable.emtwo);
        }else {
            holder.img.setImageResource(R.drawable.emthree);
        }
        holder.txtName.setText(nguoiDung.getHoTen());
        holder.txtPhone.setText(nguoiDung.getPhone());
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<NguoiDung> items){
        this.arrNguoiDung = items;
        notifyDataSetChanged();
    }
}
