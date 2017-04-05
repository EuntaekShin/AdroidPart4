package com.example.corsuradapter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private DatabaseHelper databaseHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.listView);

        boolean isopen = openDatabase();

        if(isopen){
            Cursor cursor = execRawQuery();

            startManagingCursor(cursor);

            String[] column = new String[] {"name", "age"};
            int[] to = new int[] {R.id.nameTxt, R.id.ageTxt};
            //simplecorsorAdapter(context,레이아웃id,커서객체,from(커서에들어있는 필드중 리스트 뷰 화면에 보여줄))

            SimpleCursorAdapter simpleCursorAdapter= new SimpleCursorAdapter(this, R.layout.item_layout, cursor, column, to);
            list.setAdapter(simpleCursorAdapter);
        }

       }

    private boolean openDatabase() {
        Log.d(TAG,"데이터베이스 생성 및 오픈");
        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getWritableDatabase();

        return  true;
    }

    private Cursor execRawQuery(){
        Log.d(TAG,"execRawQuery 생성 및 오픈");
        String sql = "select _id, name, age from test where age > ?";
        String[] args = {"25"};
        Cursor cursor = db.rawQuery(sql,args);
        return  cursor;
    }

}
