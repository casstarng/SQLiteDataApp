package com.example.sqlitedataapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {

    private final String TABLE_NAME = "user";
    private final String COL_ID = "ID";
    private final String COL_NAME = "NAME";
    private final String COL_EMAIL = "EMAIL";
    private final String COL_FAVORITE_SHOW = "FAVORITE_SHOW";

    private SQLiteDatabase db = this.getWritableDatabase();

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
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_FAVORITE_SHOW, favoriteShow);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public ArrayList<String> getData() {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        while(cursor.moveToNext()){
            StringBuffer sb = new StringBuffer();
            sb.append("ID: " + cursor.getString(0) + "\n");
            sb.append("Name: " + cursor.getString(1) + "\n");
            sb.append("Email: " + cursor.getString(2) + "\n");
            sb.append("Favorite TV Show: " + cursor.getString(3) + "\n");
            list.add(sb.toString());
        }

        return list;
    }

    public boolean delete(String id) {
        long result =  db.delete(TABLE_NAME, "ID = ?", new String[]{id});
        if (result == -1) return false;
        else return true;
    }

    public Boolean updateData(String id, String name, String email, String favoriteTVShow) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, id);
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_FAVORITE_SHOW, favoriteTVShow);
        long result = db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        if (result == -1) return false;
        else return true;
    }
}
