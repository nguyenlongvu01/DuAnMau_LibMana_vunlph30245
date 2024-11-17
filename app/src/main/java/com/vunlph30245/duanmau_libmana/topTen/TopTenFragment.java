package com.vunlph30245.duanmau_libmana.topTen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.vunlph30245.duanmau_libmana.Adapter.TopAdapter;
import com.vunlph30245.duanmau_libmana.DAO.PhieuMuonDAO;
import com.vunlph30245.duanmau_libmana.Model.Top;
import com.vunlph30245.duanmau_libmana.R;

import java.util.ArrayList;

public class TopTenFragment extends Fragment {
    ListView lv;
    ArrayList<Top> list;
    TopAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_top_ten, container, false);
        lv = v.findViewById(R.id.lvTop);
        PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(getActivity());
        list = (ArrayList<Top>) phieuMuonDAO.getTop();
        adapter = new TopAdapter(getActivity(),this, list);
        lv.setAdapter(adapter);
        return v;
    }
}
