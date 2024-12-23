package com.vunlph30245.duanmau_libmana.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vunlph30245.duanmau_libmana.LoaiSach.FragmentLoaiSach;
import com.vunlph30245.duanmau_libmana.Model.LoaiSach;
import com.vunlph30245.duanmau_libmana.R;

import java.util.ArrayList;



public class LoaiSachAdapter extends ArrayAdapter {
    private final Context context;
    private final ArrayList<LoaiSach> list;
    FragmentLoaiSach fragment;
    TextView tvMaLS, tvTenLS;
    ImageView imgDelLS;

    public LoaiSachAdapter(@NonNull Context context, FragmentLoaiSach fragment, ArrayList<LoaiSach> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item, null);
        }

        final LoaiSach item = list.get(position);
        if (item != null) {

            tvMaLS = v.findViewById(R.id.tvMaLS);
            tvTenLS = v.findViewById(R.id.tvTenLS);
            imgDelLS = v.findViewById(R.id.imgDelLS);

            tvMaLS.setText("Mã loại sách: " + item.getMaLoai());
            tvTenLS.setText("Tên loại sách: " + item.getTenLoai());

            imgDelLS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.xoa(String.valueOf(item.getMaLoai()));
                }
            });
        }
        int N, A;
        if (item.getTenLoai() == String.valueOf(false)) {
            tvTenLS.setTextColor(Color.GREEN);
        }

        return v;
    }
}
