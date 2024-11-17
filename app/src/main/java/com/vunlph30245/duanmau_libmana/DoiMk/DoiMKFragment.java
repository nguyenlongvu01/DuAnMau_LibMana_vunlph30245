package com.vunlph30245.duanmau_libmana.DoiMk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.vunlph30245.duanmau_libmana.DAO.ThuThuDAO;
import com.vunlph30245.duanmau_libmana.Model.ThuThu;
import com.vunlph30245.duanmau_libmana.R;

public class DoiMKFragment extends Fragment {
    TextInputEditText edPassOld,edPass,edRePass;
    Button btnSave,btnCancel;
    ThuThuDAO dao;

    public DoiMKFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_doi_m_k, container, false);
        edPassOld=v.findViewById(R.id.edPassOld);
        edPass=v.findViewById(R.id.edPassChange);
        edRePass=v.findViewById(R.id.edRePassChange);
        btnSave=v.findViewById(R.id.btnSaveUserChange);
        btnCancel=v.findViewById(R.id.btnCancelUserChange);

        dao =new ThuThuDAO(getActivity());
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPass.setText("");
                edPassOld.setText("");
                edRePass.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref =getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user=pref.getString("USERNAME","");
                if (validate()>0){
                    ThuThu thuThu=dao.getID(user);
                    thuThu.setMatKhau(edPass.getText().toString());

                    if (dao.updatePass(thuThu)>0){
                        Toast.makeText(getContext(),"thay doi mat khau thanh cong",Toast.LENGTH_SHORT).show();
                        edPass.setText("");
                        edPassOld.setText("");
                        edRePass.setText("");
                    }else {
                        Toast.makeText(getContext(),"thay doi mat khau that bai",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return v;
    }
    public int validate(){
        int check =1;
        if (edPassOld.getText().length()==0||edPass.getText().length()==0||edRePass.getText().length()==0){
            Toast.makeText(getContext(),
                    "ban phia nhap day du thong tin",
                    Toast.LENGTH_SHORT).show();
            check=-1;
        }else {
            SharedPreferences pref =getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
            String passOld = pref.getString("PASSWORD","");
            String pass = edPass.getText().toString();
            String rePass =edRePass.getText().toString();

            if (!passOld.equals(edPassOld.getText().toString())){
                Toast.makeText(getContext(),"mat khau cu sai",Toast.LENGTH_SHORT).show();
                check=-1;
            }
            if (!pass.equals(rePass)){
                Toast.makeText(getContext(),"mat khau khong trung khop",Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }
        return check;
    }
}

