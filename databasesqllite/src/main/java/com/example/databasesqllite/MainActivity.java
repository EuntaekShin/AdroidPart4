package com.example.databasesqllite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String dbName , tbName;
    SQLiteDatabase sDB;

    TextView resultTxt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText dbinPutName = (EditText)findViewById(R.id.dbNameET);
        final EditText tbinPutName = (EditText)findViewById(R.id.tbNameET);

        Button createDBbtn  =(Button)findViewById(R.id.btnCreateDB);
        resultTxt = (TextView)findViewById(R.id.tv01);
        createDBbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbName  = dbinPutName.getText().toString();
                CreateDB(dbName);
            }
        });
        Button createTBbtn  =(Button)findViewById(R.id.btnCreateTB);
        createTBbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tbName = tbinPutName.getText().toString();
                CreateTB(tbName);
                int cnt = insertMember(tbName);
                msgOutput(cnt + "개의 레코드가 추가 되었습니다");
            }
        });

        Button inserBtn= (Button)findViewById(R.id.btninsert);
        inserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tbName = tbinPutName.getText().toString();
                insertMemberParam(tbName);
            }
        });

        Button chagneBtn= (Button)findViewById(R.id.btnchange);
        chagneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tbName = tbinPutName.getText().toString();
                updateMemberParam(tbName);
            }
        });

        Button deleteBtn= (Button)findViewById(R.id.btndelete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tbName = tbinPutName.getText().toString();
                deleteMemberParams(tbName);
            }
        });

    }

    //DB생성 과정
    private  void CreateDB(String name){
        msgOutput("["+ name+"]" + "데이터베이스 생성 시작");
        try{
            sDB  = openOrCreateDatabase(name, MODE_PRIVATE,null);
            msgOutput("["+name+"]"+"데이터 베이스 생성 완료");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //table 생성 과정
    private  void CreateTB(String name){
        msgOutput("["+name+"]"+" 테이블생성 중");

        sDB.execSQL("create table if not exists " + name +"("
                +" no integer PRIMARY KEY autoincrement,  "
                +" name text, "
                +" address text ,"
                +" tel text);" );

    }
    private int insertMember(String name){
        msgOutput("["+name+"]" +"ADD Member");
        int cnt = 2;

        //레코드 추가시 추가하는 사용하는 SQL 명령: insert into 테이블명(필드명) vlaues(값1,값2..)
        //안드로이드에서는 조금 줄여서 insert를 사용한다.
        sDB.execSQL("insert into " + name+"(name,address,tel) values('영구', '서울', '647-333-444');");
        sDB.execSQL("insert into " + name+"(name,address,tel) values('맹구', '경기', '647-323-444');");


        return cnt;
    }


    //DB에 추가하기
    private int insertMemberParam(String name){
        msgOutput("인수를 이용한 레코드 추가하기!!");
        //  데이터 맵 ContentValues() -> put(키,값)
        // sDB.insert(table명, nullcoulmHack(비어있는 값을 어찌처리하느냐 보통null), contentValue)

        int cnt = 1;
        ContentValues data = new ContentValues();

        data.put("name", "임꺽정");
        data.put("address", "광주");
        data.put("tel", "1111-1111");
        sDB.insert(name,null,data);


        return  cnt;
    }

    //Db 레코드 변경하기
    private  int updateMemberParam(String name){
        msgOutput("레코드 수정하기");

        ContentValues data = new ContentValues();
        data.put("address", "Toronto");
        String[] whereArgs = {"임꺽정"};
        int i = sDB.update(name, data, "name=?",whereArgs);
        return  i;

    }

    //레코드 삭제하기 
    private  int deleteMemberParams(String name){
        msgOutput("레코드 삭제하기");

        String[] whereArgs = {"임꺽정"};
        int i = sDB.delete(name, "name=?", whereArgs);
        return  i;

    }

    private void msgOutput(String msg){
        Log.d("MainActivity", msg);
        resultTxt.append("\n"+msg);
    }


}
