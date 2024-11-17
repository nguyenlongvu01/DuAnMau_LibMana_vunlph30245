package com.vunlph30245.duanmau_libmana.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vunlph30245.duanmau_libmana.Database.DbHelper;

public class NguoidungDAO {
    private DbHelper dbHelper;

    public NguoidungDAO(Context context){
        dbHelper= new DbHelper(context);
    }
    //login
    public boolean CheckLogin(String username,String password){
        SQLiteDatabase sqLiteDatabase= dbHelper.getReadableDatabase();

        Cursor cursor= sqLiteDatabase.rawQuery("SELECT *FROM NGUOIDUNG WHERE tendangnhap=? AND matkhau=? ",new String[]{username,password});
        return cursor.getCount() > 0;
    }
    //register
    public boolean Register(String username,String password,String hoten){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("maTT",username);
        contentValues.put("hoTen",password);
        contentValues.put("matKhau",hoten);

        long check=sqLiteDatabase.insert("ThuThu",null,contentValues);
        if(check!=-1){
            return true;
        }
        return false;
    }
    //forgot
    public String Forgotpassword(String email){
        SQLiteDatabase sqLiteDatabase =dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT matkhau FROM NGUOIDUNG WHERE tendangnhap=?",new String[]{email});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            return cursor.getString(0);
        }else {
            return "";
        }
    }
}
