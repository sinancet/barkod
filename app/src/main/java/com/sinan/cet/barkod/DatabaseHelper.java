package com.sinan.cet.barkod;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper {
    public static  final String DATABASE_NAME = "Student.db";
    public static  final String TABLE_NAME = "student_table";
    public static  final String COL_1 = "ID";
    public static  final String COL_2 = "NAME";
    public static  final String COL_3 = "SURNAME";
    public static  final String COL_4 = "MARKS";
    public  static  String donusDegeri;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,  null, 1);
        SQLiteDatabase db = this.getWritableDatabase();

    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USER_TABLE = ("create table student_table (ID integer primary key autoincrement,NAME text,SURNAME text,MARKS integer)");
        db.execSQL(CREATE_USER_TABLE);
    }


    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);

    }
    public int KayitSorgula(String gelenDeger){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorKayit = db.rawQuery("SELECT * FROM "+ TABLE_NAME +" WHERE MARKS  = " + gelenDeger , null);

       return cursorKayit.getCount();


    }


    public void sorgulaTC(String COL_4) {
        SQLiteDatabase db = this.getWritableDatabase();


        Cursor c = db.rawQuery("SELECT MARKS FROM "+TABLE_NAME+" WHERE Name like '"+
                COL_4+"'", null);
        if (c != null ) {
            if  (c.moveToFirst()) {
                do {

                }while (c.moveToNext());
            }
        }

    }



    public boolean insertData (String name,String surname,String marks){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1 )
            return  false;
        else
            return  true;

    }
    public Cursor getAllData(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);

        return res;

    }


}
