package com.vunlph30245.duanmau_libmana.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vunlph30245.duanmau_libmana.Database.DbHelper;
import com.vunlph30245.duanmau_libmana.Model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    private SQLiteDatabase db;
    private DbHelper dbhelper;

    public ThuThuDAO(Context context) {
        DbHelper dbhelper=new DbHelper(context);
        db=dbhelper.getWritableDatabase();
    }
    public void ThuThuDAO1(Context context) {
        dbhelper=new DbHelper(context);
    }
    public boolean Register(String username,String password,String hoten){
        SQLiteDatabase sqLiteDatabase=dbhelper.getWritableDatabase();

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
    public long insert(ThuThu obj){
        ContentValues values =new ContentValues();
        values.put("maTT",obj.getMaTT());
        values.put("hoTen",obj.getHoTen());
        values.put("matKhau",obj.getMatKhau());
        return db.insert("ThuThu",null,values);
    }
    public int updatePass(ThuThu obj){
        ContentValues values =new ContentValues();
        values.put("hoTen",obj.getHoTen());
        values.put("matKhau",obj.getMatKhau());
        return db.update("ThuThu",values,"maTT=?",new String[]{obj.getMaTT()});
    }
    public int delete(String id){
        return db.delete("ThuThu","maTT=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<ThuThu> getdata(String sql, String...selectionArgs){
        List<ThuThu>list=new ArrayList<ThuThu>();
        Cursor c =db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            ThuThu obj =new ThuThu();
            obj.setMaTT(c.getString(c.getColumnIndex("maTT")));
            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            obj.setMatKhau(c.getString(c.getColumnIndex("matKhau")));
            list.add(obj);
        }
        return list;
    }
    public List<ThuThu> getAll(){
        String sql="select * from ThuThu";
        return getdata(sql);
    }
    public ThuThu getID(String id){
        String sql="select * from ThuThu WHERE maTT=?";
        List<ThuThu>list=getdata(sql,id);
        return list.get(0);
    }
    public int checkLogin(String id, String password){
        String sql="select * from ThuThu where maTT=? and matKhau=?";
        List<ThuThu> list =getdata(sql,id,password);
        if (list.size()==0){
            return -1;
        }
        return 1;
    }
}
