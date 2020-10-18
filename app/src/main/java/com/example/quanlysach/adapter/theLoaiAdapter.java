package com.example.quanlysach.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlysach.R;
import com.example.quanlysach.dao.TheLoaiDAO;
import com.example.quanlysach.model.TheLoai;

import java.util.List;

public class theLoaiAdapter extends BaseAdapter {
    List<TheLoai> arrTheLoai;
    public Activity context;
    public LayoutInflater inflater;
    TheLoaiDAO theLoaiDAO;

    public theLoaiAdapter(Activity context, List<TheLoai> arrayTheLoai) {
        super();
        this.context = context;
        this.arrTheLoai = arrayTheLoai;
        inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        theLoaiDAO=new TheLoaiDAO(context);
    }

    @Override
    public int getCount() {
        return arrTheLoai.size();
    }

    @Override
    public Object getItem(int position) {
        return arrTheLoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
 public static class ViewHolder{
        ImageView imageView;
        TextView txtMaTheLoai;
        TextView txtTenTheLoai;
        ImageView imgDelete;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.item_theloai,null);
            viewHolder.imageView=convertView.findViewById(R.id.ivIcon);
            viewHolder.txtMaTheLoai=convertView.findViewById(R.id.tvMaTheLoai);
            viewHolder.txtTenTheLoai=convertView.findViewById(R.id.tvTenTheLoai);
            viewHolder.imgDelete=convertView.findViewById(R.id.ivDelete);
            viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theLoaiDAO.deleteTheLoaiByID(arrTheLoai.get(position).getMaTheLoai());
                    arrTheLoai.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)convertView.getTag();

        }
        TheLoai theLoai=arrTheLoai.get(position);
        viewHolder.imageView.setImageResource(R.drawable.cateicon);
        viewHolder.txtMaTheLoai.setText(theLoai.getMaTheLoai());
        viewHolder.txtTenTheLoai.setText(theLoai.getTenTheLoai());
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<TheLoai> list){
        this.arrTheLoai=list;
        notifyDataSetChanged();
    }
}
