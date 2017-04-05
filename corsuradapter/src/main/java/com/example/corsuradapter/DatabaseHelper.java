package com.example.corsuradapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 2017-03-29.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "testDB";
    String sql;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        sql ="CREATE TABLE test (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age TEXT);";
        db.execSQL(sql);

        db.execSQL("INSERT INTO test VALUES(null, '홍길구', '22');");
        db.execSQL("INSERT INTO test VALUES(null, '홍길팔', '23');");
        db.execSQL("INSERT INTO test VALUES(null, '홍길칠', '24');");
        db.execSQL("INSERT INTO test VALUES(null, '홍길육', '25');");
        db.execSQL("INSERT INTO test VALUES(null, '홍길오', '26');");
        db.execSQL("INSERT INTO test VALUES(null, '홍길사', '27');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS test" );
        onCreate(db);
    }
}
