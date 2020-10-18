package com.example.quanlysach.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlysach.R;
import com.example.quanlysach.dao.HoaDonChiTietDAO;
import com.example.quanlysach.dao.HoaDonDAO;
import com.example.quanlysach.model.hoaDon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class hoaDonAdapter extends BaseAdapter implements Filterable {
    public Activity context;
    List<hoaDon> hoaDonList;
    List<hoaDon> hoaDonListSort;
    public LayoutInflater inflater;
    HoaDonDAO hoaDonDAO;
    private Filter filter;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");

    public hoaDonAdapter(Activity context, List<hoaDon> hoaDonList) {
        super();
        this.context = context;
        this.hoaDonList = hoaDonList;
        this.hoaDonListSort=hoaDonList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
        this.hoaDonDAO = new HoaDonDAO(context);
    }

    @Override
    public int getCount() {
        return hoaDonList.size();
    }

    @Override
    public Object getItem(int position) {
        return hoaDonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public Filter getFilter() {
        if(filter==null){
            filter=new CustomFilter();
        }
        return filter;
    }
    public void ResetData(){
        hoaDonList=hoaDonListSort;
    }
    public class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results=new FilterResults();
            if(constraint==null || constraint.length()==0){
                results.values=hoaDonListSort;
                results.count=hoaDonListSort.size();
            }
            else {
                List<hoaDon> lsHoaDon=new ArrayList<hoaDon>();
                for(hoaDon h:hoaDonList){
                        if(h.getMaHoaDon().toUpperCase().startsWith(constraint.toString().toUpperCase()))lsHoaDon.add(h);
                }
                results.values=lsHoaDon;
                results.count=lsHoaDon.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
                if(results.count == 0){
                    notifyDataSetInvalidated();
                }else {
                    hoaDonList=(List<hoaDon>) results.values;
                    notifyDataSetChanged();
                }
        }
    }

    public static class ViewHolder{
        ImageView imageView;
        TextView tvHoaDon,tvNgayMua;
        ImageView imgDelete;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.item_hoadon,null);
            viewHolder.imageView=convertView.findViewById(R.id.ivIcon);
            viewHolder.tvHoaDon=convertView.findViewById(R.id.tvMaHoaDon);
            viewHolder.tvNgayMua=convertView.findViewById(R.id.tvNgayMua);
            viewHolder.imgDelete=convertView.findViewById(R.id.ivDelete);
            viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if
                    (hoaDonChiTietDAO.checkHoaDon(hoaDonList.get(position).getMaHoaDon())){
                        Toast.makeText(context,"Bạn phải xoá hoá đơn chi tiết trước khi xoá hoá đơn này",Toast.LENGTH_LONG).show();
                    }
                    else {

                        hoaDonDAO.DeleteHoaDonById(hoaDonList.get(position).getMaHoaDon());
                        hoaDonList.remove(position);
                        notifyDataSetChanged();
                    }
                }
            });
            convertView.setTag(viewHolder);

        }
        else {
            viewHolder=(ViewHolder)convertView.getTag();

        }
        hoaDon hoaDon=hoaDonList.get(position);
        viewHolder.imageView.setImageResource(R.drawable.hdicon);
        viewHolder.tvHoaDon.setText(hoaDon.getMaHoaDon());
        viewHolder.tvNgayMua.setText(simpleDateFormat.format(hoaDon.getNgayMua()));
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataSet(List<hoaDon> arrHoaDon){
        this.hoaDonList=arrHoaDon;
        notifyDataSetChanged();
    }
}
