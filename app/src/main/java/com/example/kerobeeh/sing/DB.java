package com.example.kerobeeh.sing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.net.IDN;
import java.util.ArrayList;

/**
 * Created by kero beeh on 9/22/2017.
 */

public class DB extends SQLiteOpenHelper {
    public static final String DBNAME = "data.db";

    public DB(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table sign (id INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT ,password TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS sign ");
        onCreate(db);

    }

    public boolean insertdata(String name, String pass) {

        SQLiteDatabase dp = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("password", pass);
        long result = dp.insert("sign", null, contentValues);

        if (result == -1) {
            return false;
        } else
            return true;
    }


   /* public boolean insertdataAll (String name , String password) {

        SQLiteDatabase dp = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("passwrod", password);
        long result = dp.insert("sign2", null, contentValues);

       if (result == -1) {
            return false;
        } else return true;
    }*/

    public ArrayList getAllrecord() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase dp = this.getReadableDatabase();
        Cursor res = dp.rawQuery("select * from sign", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            String t1 = res.getString(0);
            String t2 = res.getString(1);
            String t3 = res.getString(2);
            arrayList.add(t1 + "  " + t2 + "  " + t3);
            res.moveToFirst();

        }
        return arrayList;
    }

     boolean ch(String Name, String pass) {

        SQLiteDatabase db = this.getReadableDatabase();
        String select = "select * from sing where name =? and password =?";

        Cursor res = db.rawQuery(select,new String[]{Name,pass});
         if(res.moveToFirst())
             return true;
         else
            return false;


    }
        // public boolean updateDate (final String ID , String name , String password) {
        //SQLiteDatabase dp = this.getWritableDatabase();
        // ContentValues contentValues = new ContentValues();
        // contentValues.put("name", name);
        // contentValues.put("passwrod", password);
        // long result = dp.insert("sign", contentValues , "ID=?" , new String(){ID});
        //  return true;

    }










