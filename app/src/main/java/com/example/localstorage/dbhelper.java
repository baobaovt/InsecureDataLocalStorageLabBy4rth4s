package com.example.localstorage;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public  class dbhelper extends SQLiteOpenHelper {

    public dbhelper(Context context) {
        super(context, "baonguyen2.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS ntbvt(username VARCHAR, password VARCHAR);");
        db.execSQL("INSERT INTO ntbvt VALUES('admin','password');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }

    public Boolean AddUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("username",username);
        value.put("password",password);

        long result = db.insert("ntbvt",null ,value);
        if(result==-1){
            return false;

        }else{
            return true;
        }

    }
    public Cursor Search(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from ntbvt", null);
        return  cursor;
    }
    public Cursor FindName(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from ntbvt where username='"+ username + "'", null);
        return cursor;
    }
}
