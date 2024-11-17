package com.vunlph30245.duanmau_libmana;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vunlph30245.duanmau_libmana.DAO.ThuThuDAO;

public class LoginActivity extends AppCompatActivity {
    EditText edUserName,edPassword;
    Button btnLogin,btnDangky;
    CheckBox chkRememberPass;
    String strUser,strPass;
    ThuThuDAO dao;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        edUserName=findViewById(R.id.edUserName);
        edPassword=findViewById(R.id.edPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnDangky=findViewById(R.id.btnDangky);
        chkRememberPass=findViewById(R.id.chkRememberPass);
        dao=new ThuThuDAO(this);

        SharedPreferences pref =getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String user =pref.getString("USERNAME","");
        String pass =pref.getString("PASSWORD","");
        Boolean rem =pref.getBoolean("REMEMBER",false);

        edUserName.setText(user);
        edPassword.setText(pass);
        chkRememberPass.setChecked(rem);

        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }
    public void rememberUser(String u,String p,boolean status){
        SharedPreferences pref =getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit =pref.edit();
        if (!status){
            edit.clear();
        }else {
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        edit.commit();
    }
    public void checkLogin(){
        strUser=edUserName.getText().toString();
        strPass=edPassword.getText().toString();
        if (strUser.isEmpty()||strPass.isEmpty()){
            Toast.makeText(getApplicationContext(),"khong duoc de trong",Toast.LENGTH_SHORT).show();
        }else {
            if (dao.checkLogin(strUser,strPass)>0){
                Toast.makeText(getApplicationContext(),"Login thanh cong",Toast.LENGTH_SHORT).show();
                rememberUser(strUser,strPass,chkRememberPass.isChecked());
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("user",strUser);
                startActivity(i);
                finish();
            }else {
                Toast.makeText(getApplicationContext(),"nhap sai",Toast.LENGTH_SHORT).show();
            }
        }
    }
}