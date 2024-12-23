package com.vunlph30245.duanmau_libmana.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vunlph30245.duanmau_libmana.DAO.LoaiSachDAO;
import com.vunlph30245.duanmau_libmana.Model.LoaiSach;
import com.vunlph30245.duanmau_libmana.Model.Sach;
import com.vunlph30245.duanmau_libmana.R;
import com.vunlph30245.duanmau_libmana.Sach.SachFragment;

import java.util.List;

public class SachAdapter extends ArrayAdapter<Sach> {
    Context context;
    SachFragment fragment;
    List<Sach> list;
    TextView tvMaSach,tvTenSach,tvGiaThue,tvLoai,tvNhacungcap;
    ImageView imgDel;

    public SachAdapter(@NonNull Context context, SachFragment fragment, List<Sach> list) {
        super(context, 0,list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v ==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.sach_item,null);
        }
        final Sach item = list.get(position);
        if (item != null){
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(item.getMaLoai()));
            tvMaSach = v.findViewById(R.id.tvMaSach);
            tvMaSach.setText("Mã sách: " +item.getMaSach());

            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách: " +item.getTenSach());
            tvGiaThue  = v.findViewById(R.id.tvGiaThue);
            tvGiaThue.setText("Giá Thuê: " +item.getGiaThue());
            tvLoai = v.findViewById(R.id.tvLoai);
            tvLoai.setText("Loại sách: " +loaiSach.getTenLoai());
            tvNhacungcap = v.findViewById(R.id.tvGNhacungcap);
            tvNhacungcap.setText("Tác giả: "+item.getNhacungcap());
            imgDel = v.findViewById(R.id.imgDeleteLS);
        }

//            if (item.){
//                tvNhacungcap.setTextColor(Color.RED);
//            } else {
//                tvNhacungcap.setTextColor(Color.BLUE);
//            }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xoa
                fragment.xoa(String.valueOf(item.getMaSach()));
            }
        });


        return v;

    }
}
