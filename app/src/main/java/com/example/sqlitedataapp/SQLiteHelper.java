package com.example.sqlitedataapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private final String TABLE_NAME = "user";
    private final String COL_ID = "ID";
    private final String COL_NAME = "NAME";
    private final String COL_EMAIL = "EMAIL";
    private final String COL_FAVORITE_SHOW = "FAVORITE_SHOW";

    public SQLiteHelper(Context context) {
        super(context, "UserData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +" (" +
                ""+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                ""+COL_NAME+" TEXT," +
                ""+COL_EMAIL+" TEXT," +
                ""+COL_FAVORITE_SHOW+" TEXT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insert(String name, String email, String favoriteShow){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_FAVORITE_SHOW, favoriteShow);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }
}
