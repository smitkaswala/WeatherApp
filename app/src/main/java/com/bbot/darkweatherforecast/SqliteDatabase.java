package com.bbot.darkweatherforecast;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class    SqliteDatabase extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CitySearch";
    private static final String TABLE_CONTACTS = "city";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_CITY = "columncity";
    private static final String COLUMN_COUNTRY = "columncountry";
    private static final String COLUMN_STATE = "columnstate";
    private static final String COLUMN_KEY = "columnkey";


    public SqliteDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String  query = "CREATE TABLE " + TABLE_CONTACTS + "(" + COLUMN_ID
                + " INTEGER PRIMARY KEY,"
                + COLUMN_CITY + " TEXT,"
                + COLUMN_COUNTRY + " TEXT,"
                + COLUMN_STATE + " TEXT,"
                + COLUMN_KEY + " TEXT" + ")";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);

    }


    public boolean addbook(AddLocationClass addLocationData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_CITY, addLocationData.city);
        contentValues.put(COLUMN_COUNTRY, addLocationData.country);
        contentValues.put(COLUMN_STATE, addLocationData.stateloca);
        contentValues.put(COLUMN_KEY, addLocationData.key);
        db.insert(TABLE_CONTACTS, null, contentValues);
        return true;
    }


    public ArrayList<AddLocationClass> listContacts() {
        String sql = "select * from " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<AddLocationClass> storeContacts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String id = ((cursor.getString(0)));
                String city = cursor.getString(1);
                String country = cursor.getString(2);
                String stateloca = cursor.getString(3);
                String key = cursor.getString(4);
                storeContacts.add(new AddLocationClass(id, city, country, stateloca, key));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return storeContacts;
    }

}
