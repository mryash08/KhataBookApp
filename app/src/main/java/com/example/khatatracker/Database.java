package com.example.khatatracker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.SurfaceControl;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    public static String DB_NAME = "DATA.db";
    Context activity;
    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        activity=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String q = "CREATE TABLE khatabook(id integer primary key autoincrement,name text,mobile text,price text,gave text,paymentcheck text ,paymentmethod1 text , duedate text,paymentcomplatedate text,adress text,whatsapp text)";

        String q1 = "CREATE TABLE transaction1(id integer primary key autoincrement, phone text ,recive text,paid text,date text,time text,notes text)";
        sqLiteDatabase.execSQL(q);
        sqLiteDatabase.execSQL(q1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS khatabook");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS transaction1");
        onCreate(sqLiteDatabase);

    }

    public void addtransaction(AddTrans a){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone",a.getPhone());
        contentValues.put("recive",a.getRecive());
        contentValues.put("paid",a.getPaid());
        contentValues.put("date",a.getDate());
        contentValues.put("time",a.getTime());
        contentValues.put("notes",a.getNote());

        long res = sqLiteDatabase.insert("transaction1",null,contentValues);
        if(res == -1){
            Toast.makeText(((Addtransaction)activity), "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(((Addtransaction)activity), "Success", Toast.LENGTH_SHORT).show();
        }
    }

    public void insertdata(String name , String mobile, String price , String gave , String paymentcheck , String paymentmethod1
    ,String duedate , String paymentcomplatedate , String adress ,  String whatsapp){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("mobile",mobile);
        contentValues.put("price",price);
        contentValues.put("gave",gave);
        contentValues.put("paymentcheck",paymentcheck);
        contentValues.put("paymentmethod1",paymentmethod1);
        contentValues.put("duedate",duedate);
        contentValues.put("paymentcomplatedate",paymentcomplatedate);
        contentValues.put("adress",adress);
        contentValues.put("whatsapp",whatsapp);

        sqLiteDatabase.insert("khatabook",null,contentValues);
    }


    public List<AddTrans> readtransaction(){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        List<AddTrans>  addTransList=new ArrayList<>();
        String q = "select * from khatabook,transaction1 where khatabook.mobile = transaction1.phone order by transaction1.id desc";
        Cursor cursor = sqLiteDatabase.rawQuery(q,null);
        if (cursor.moveToFirst()){
            do{
                @SuppressLint("Range") String mobile = cursor.getString(cursor.getColumnIndex("mobile"));
                @SuppressLint("Range") String recive = cursor.getString(cursor.getColumnIndex("recive"));
                @SuppressLint("Range") String paid = cursor.getString(cursor.getColumnIndex("paid"));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
                @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex("time"));
                @SuppressLint("Range") String note = cursor.getString(cursor.getColumnIndex("notes"));

                AddTrans a = new AddTrans(mobile,recive,paid,date,time,note);
                addTransList.add(a);
            }while (cursor.moveToNext());
        }

        return addTransList;
    }


    public List<datamodel> readdata(){
        List<datamodel> data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String q = " select * from khatabook";
        Cursor cursor = sqLiteDatabase.rawQuery(q,null);
        if (cursor.moveToFirst()){
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String mobile = cursor.getString(cursor.getColumnIndex("mobile"));
                @SuppressLint("Range") String price = cursor.getString(cursor.getColumnIndex("price"));
                @SuppressLint("Range") String gave = cursor.getString(cursor.getColumnIndex("gave"));
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String paymentcheck = cursor.getString(cursor.getColumnIndex("paymentcheck"));
                @SuppressLint("Range") String paymentmethod1 = cursor.getString(cursor.getColumnIndex("paymentmethod1"));
                @SuppressLint("Range") String duedate = cursor.getString(cursor.getColumnIndex("duedate"));
                @SuppressLint("Range") String paymentcomplatedate = cursor.getString(cursor.getColumnIndex("paymentcomplatedate"));
                @SuppressLint("Range") String adress = cursor.getString(cursor.getColumnIndex("adress"));
                @SuppressLint("Range") String whatsapp = cursor.getString(cursor.getColumnIndex("whatsapp"));

                datamodel d = new datamodel(name , mobile,price,gave,id,paymentcheck,paymentmethod1,duedate,paymentcomplatedate,adress,whatsapp);
                data.add(d);
            }while (cursor.moveToNext());
        }



        return data;

    }

    public void updatedata(String name , String mobile, String price , String paymentcheck , String paymentmethod1, String adress,String whatsapp){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name",name);
        contentValues.put("mobile",mobile);
        contentValues.put("price",price);
        contentValues.put("paymentcheck",paymentcheck);
        contentValues.put("paymentmethod1",paymentmethod1);
        contentValues.put("adress",adress);
        contentValues.put("whatsapp",whatsapp);

        sqLiteDatabase.update("khatabook",contentValues,"name=?",new String[]{name});

    }

    public void delete(String id){
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        sqLiteDatabase.delete("khatabook","id=?",new String[]{id});
    }
}
