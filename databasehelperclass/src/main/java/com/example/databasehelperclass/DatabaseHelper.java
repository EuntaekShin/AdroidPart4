package com.example.databasehelperclass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by user on 2017-03-29.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    final static String TAG = "DatabaseHelper";
    String sql;

    public DatabaseHelper(Context context) {
        super(context, "Member.db" , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"onCreate()");
        sql ="CREATE TABLE member(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, id TEXT, pw TEXT);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG,"onUpgrade() 호출");
        sql = "DROP TABLE IF EXISTS member";
        db.execSQL(sql);
        onCreate(db);
    }
}
