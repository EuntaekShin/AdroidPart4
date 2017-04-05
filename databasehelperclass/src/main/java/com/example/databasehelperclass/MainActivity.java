package com.example.databasehelperclass;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "MainActivity";

    DatabaseHelper databaseHelper;

    Button insertBtn, deleteBtn, updateBtn, selectBtn;
    TextView selectTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        insertBtn = (Button)findViewById(R.id.btninsert);
        deleteBtn = (Button)findViewById(R.id.btnDelete);
        updateBtn = (Button)findViewById(R.id.btnupdate);
        selectBtn = (Button)findViewById(R.id.btnSelect);

        selectTxt = (TextView)findViewById(R.id.tvSelect);

        insertBtn.setOnClickListener(listener);
        deleteBtn.setOnClickListener(listener);
        updateBtn.setOnClickListener(listener);
        selectBtn.setOnClickListener(listener);

    }

    View.OnClickListener listener = new View.OnClickListener(){
        SQLiteDatabase sDB;
        ContentValues data;

        public void onClick(View v){
            switch(v.getId()){
                case R.id.btninsert:
                    try{
                        sDB = databaseHelper.getWritableDatabase();

//                      data = new ContentValues();
//                      data.put("name", "유재석");
//                      data.put("id", "test");
//                      data.put("pw", "test1234");

                        // sDB.insert("member", null, data);

                        String sql = "INSERT INTO member (_id, name, id, pw) VALUES(null, '유재석', 'test','test1234')";
                        sDB.execSQL(sql);
                        String sql1 = "INSERT INTO member (_id, name, id, pw) VALUES(null, '김말똥', 'test1','test1111')";
                        sDB.execSQL(sql1);

                        databaseHelper.close();
                        Toast.makeText(getBaseContext(),"레코드추가완료!!", Toast.LENGTH_SHORT).show();
                    }catch(Exception e){
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(), "레코드 추가 오류!!", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.btnDelete:
                    try{
                        sDB = databaseHelper.getWritableDatabase();

//                      sDB.delete("member",null, null); //조건없이 모든 레코드를 삭제

                        String sql = "DELETE FROM member;";
                        sDB.execSQL(sql);

                        databaseHelper.close();
                        Toast.makeText(getBaseContext(), "삭제 완료!!", Toast.LENGTH_SHORT).show();
                    }catch(Exception e){
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(), "삭제오류!!", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.btnupdate:
                    try{
                        sDB = databaseHelper.getWritableDatabase();

//                      data = new ContentValues();
//                      data.put("name", "홍길동");
//                      String[] whereArgs = {"유재석"};
//                      sDB.update("member",data, "name=?",whereArgs);

                        String sql = "UPDATE member SET name='홍길동' WHERE name='유재석';";
                        sDB.execSQL(sql);

                        databaseHelper.close();
                        Toast.makeText(getBaseContext(), "업데이트완료!!", Toast.LENGTH_SHORT).show();
                    }catch(Exception e){
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(), "업데이트오류!!", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.btnSelect:
                    try{
                        sDB = databaseHelper.getReadableDatabase();

                        String[] fields = {"name", "id", "pw"};
                        Cursor cursor = sDB.query("member", fields, null, null, null, null, null);

//                      String sql = "SELECT name, id, pw FROM member";
//                      Cursor cursor = sDB.rawQuery(sql, null);

                        StringBuffer sb = new StringBuffer();
                        while(cursor.moveToNext()){
                            String name = cursor.getString(0);
                            String id = cursor.getString(1);
                            String pw = cursor.getString(2);

                            sb.append("name : "+name).append(", id : "+id).append(", pw : "+pw+"\n");
                        }
                        selectTxt.setText(sb.toString());

                        cursor.close();
                        databaseHelper.close();
                        Toast.makeText(getBaseContext(), "조회완료!!", Toast.LENGTH_SHORT).show();
                    }catch(Exception e){
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(), "조회오류!!", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

}