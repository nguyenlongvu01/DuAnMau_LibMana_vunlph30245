package com.vunlph30245.duanmau_libmana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vunlph30245.duanmau_libmana.DAO.NguoidungDAO;

public class RegisterActivity extends AppCompatActivity {
    private NguoidungDAO dao;
    EditText edt_rePassword,edt_Password,edt_UserName;
    Button btnRegister,btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        edt_rePassword=findViewById(R.id.edt_rePassword);
        edt_Password=findViewById(R.id.edt_Password);
        edt_UserName=findViewById(R.id.edt_UserName);
        btnRegister=findViewById(R.id.btnRegister);
        btn_back=findViewById(R.id.btn_back);
        dao= new NguoidungDAO(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=edt_UserName.getText().toString();
                String pass=edt_Password.getText().toString();
                String rePass=edt_rePassword.getText().toString();

                if(!pass.equals(rePass)){
                    Toast.makeText(RegisterActivity.this, "Nhap 2 mat khau khong trung nhau", Toast.LENGTH_SHORT).show();
                }else{
                    boolean check=dao.Register(user,pass,rePass);
                    if (check){
                        Toast.makeText(RegisterActivity.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        finish();
                    }else{
                        Toast.makeText(RegisterActivity.this, "Dang ky that bai", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}