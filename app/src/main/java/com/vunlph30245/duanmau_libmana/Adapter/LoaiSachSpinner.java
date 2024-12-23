package com.vunlph30245.duanmau_libmana.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vunlph30245.duanmau_libmana.Model.LoaiSach;
import com.vunlph30245.duanmau_libmana.R;

import java.util.ArrayList;



public class LoaiSachSpinner extends ArrayAdapter {
    Context context;
    ArrayList<LoaiSach> lists;
    TextView tvMaLoaiSach,tvTenLoaiSach;
    public LoaiSachSpinner(@NonNull Context context, ArrayList<LoaiSach> lists) {
        super(context, 0,lists);
        this.context = context;
        this.lists = lists;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item_spinner,null);
        }
        final LoaiSach item = lists.get(position);
        if (item != null){
            tvMaLoaiSach = v.findViewById(R.id.tvMaLS);
            tvMaLoaiSach.setText(item.getMaLoai()+".");

            tvTenLoaiSach = v.findViewById(R.id.tvTenLS);
            tvTenLoaiSach.setText(item.getTenLoai());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v ==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item_spinner,null);
        }
        final LoaiSach item = lists.get(position);
        if (item != null){
            tvMaLoaiSach = v.findViewById(R.id.tvMaLS);
            tvMaLoaiSach.setText(item.getMaLoai()+".");

            tvTenLoaiSach = v.findViewById(R.id.tvTenLS);
            tvTenLoaiSach.setText(item.getTenLoai());
        }
        return v;
    }
}

