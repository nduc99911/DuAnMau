package com.example.quanlysach.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlysach.R;
import com.example.quanlysach.dao.SachDAO;
import com.example.quanlysach.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class sachAdapter extends BaseAdapter implements Filterable {
    public Activity context;
    List<Sach>  sachList;
    List<Sach>  arrSortSach;
    private Filter filter;
    public LayoutInflater inflater;
    SachDAO sachDAO;

    public sachAdapter(Activity context, List<Sach> sachList) {
        super();
        this.context = context;
        this.sachList = sachList;
        this.arrSortSach=sachList;
        inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        sachDAO=new SachDAO(context);
    }

    @Override
    public int getCount() {
        return sachList.size();
    }

    @Override
    public Object getItem(int position) {
        return sachList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }




    public static class viewHolder{
        ImageView imageView;
        TextView tvName,tvSoLuong,tvPrice;
        ImageView imgDelete;
}

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder;
        if(convertView==null){
            viewHolder=new viewHolder();
            convertView=inflater.inflate(R.layout.item_book,null);
            viewHolder.imageView=convertView.findViewById(R.id.ivIcon);
            viewHolder.tvName=convertView.findViewById(R.id.tvBookName);
            viewHolder.tvSoLuong=convertView.findViewById(R.id.tvSoLuong);
            viewHolder.tvPrice=convertView.findViewById(R.id.tvBookPrice);
            viewHolder.imgDelete=convertView.findViewById(R.id.ivDelete);
            viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sachDAO.deleteSachByMaSach(sachList.get(position).getMaSach());
                    sachList.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(viewHolder);

        }
        else {
            viewHolder= (sachAdapter.viewHolder) convertView.getTag();


        }
        Sach sach=sachList.get(position);
        viewHolder.imageView.setImageResource(R.drawable.bookicon);
        viewHolder.tvName.setText(sach.getMaSach());
        viewHolder.tvPrice.setText(String.valueOf(sach.getGiaBia()));
        viewHolder.tvSoLuong.setText(String.valueOf(sach.getSoLuong()));
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataSet(List<Sach> list){
        this.sachList=list;
        notifyDataSetChanged();

    }
    public void resetData() {
        sachList = arrSortSach;
    }
    @Override
    public Filter getFilter() {
        if (filter == null)
            filter = new CustomFilter();
        return filter;
    }
    private class CustomFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            //luu tru ket qua loc
            FilterResults   filterResults=new FilterResults();
            if(constraint==null || constraint.length()==0){
                filterResults.values=arrSortSach;
                filterResults.count=arrSortSach.size();
            }
            else {
                List<Sach> lsSach=new ArrayList<>();
                for(Sach s:sachList){
                    if(s.getMaSach().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                    {lsSach.add(s); }
                }
                filterResults.values=lsSach;
                filterResults.count=lsSach.size();
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                sachList = (List<Sach>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
