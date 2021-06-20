package com.bbot.darkweatherforecast;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    //Initialize Database Name and Table Name

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SEARCH.DB";
    private static final String TABLE_NAME = "Seaech_Table";
    private static final String ID = "ID";
    private static final String CITYKEY = "CITYKEY";
    private static final String CITY = "CITY";
    private static final String COUNTRY = "COUNTRY";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CITYKEY + " TEXT, " + CITY + " TEXT, " + COUNTRY + " TEXT);";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    public Boolean insertuserdata(String cityKey, String city, String country) {
        SQLiteDatabase DB = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CITYKEY, cityKey);
        contentValues.put(CITY, city);
        contentValues.put(COUNTRY, country);

        long result = DB.insert(TABLE_NAME, null, contentValues);
        if (result >= 0) {

            return true;

        } else {

            return false;

        }

    }

     public ArrayList<AddLocationData> getAllReminder(){

     ArrayList<AddLocationData> reminderList = new ArrayList<>();
     SQLiteDatabase db = getReadableDatabase();

     Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);

     if (cursor.moveToFirst()){

        do {

            AddLocationData reminder = new AddLocationData();
            reminder.setKey(cursor.getString(1));
            reminder.setCity(cursor.getString(2));
            reminder.setCountry(cursor.getString(3));
            reminderList.add(reminder);

        }
        while (cursor.moveToNext());

     }


     return reminderList;
   }


}
