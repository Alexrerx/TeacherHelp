package com.example.alexey.teacherhelp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper {


    private static DataHelper dataHelper;

    public DataHelper(Context context){
        super(context,"myDB",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("MyLog","DB created");
        db.execSQL("create table students ("
                + "id integer primary key autoincrement," +
                "FIO text," + "subject text," + "duration integer," + "time text," + "day text," +
                "address text,"
        + "price integer" + ");");
    }

    public static DataHelper getInstance(Context context){

        if (dataHelper == null) dataHelper = new DataHelper(context);

        return dataHelper;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void deleteStudentById(SQLiteDatabase db,int id){
        db.delete("students","id = " + id,null);
    }
    public void deleteStudentByFIO(SQLiteDatabase db,String FIO){
        db.delete("students","FIO = " + FIO,null);
    }


    public List <Student> getAll(){

        Cursor c = dataHelper.getReadableDatabase().query("students",
                null,null,null,
                null,null,null);
        ArrayList <Student> students = new ArrayList<>();
        if (c.moveToFirst()){
            int idColIndex = c.getColumnIndex("id");
            int FIOColIndex = c.getColumnIndex("FIO");
            int subjectColIndex = c.getColumnIndex("subject");
            int durationColIndex = c.getColumnIndex("duration");
            int timeColIndex = c.getColumnIndex("time");
            int dayColIndex = c.getColumnIndex("day");
            int addressColIndex = c.getColumnIndex("address");
            int priceColIndex = c.getColumnIndex("price");

            do {
                Log.d("MyLogs","----Insert----");
                students.add(new Student(c.getInt(idColIndex),c.getString(FIOColIndex),
                        c.getString(subjectColIndex),c.getInt(durationColIndex),
                        c.getString(timeColIndex),
                        c.getString(dayColIndex),
                        c.getString(addressColIndex),
                        c.getInt(priceColIndex)));

            }while (c.moveToNext());


        }
        c.close();
        return students;
    }
    public String[] Update(int position){
        String[] data = new String[7];
        Cursor c = dataHelper.getReadableDatabase().query("students",null,null,
                null,null,null,null);

        return data;
    }



}
