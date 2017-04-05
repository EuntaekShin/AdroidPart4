package com.example.datamangebypreference;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
/*

프리퍼런스 사용법
1. preferenceActivity 상속 받아 사용
2. 상속을 받지 않고 어디서나 사용
 우리는 2번째 것을 사용
 정의 -> sharedPreference setting;
 불러오기 -> getSharedPreferences(xml파일이름,0)->여기서 제로는 읽고 쓰기가 다가능하다
             getSharedPreferences(int mode) ->현재 엑티비티 이름으로 xml이 만들어짐
             preferenceManager.getDefaultSharedpreferences(Context)
 기록하기 -> editor를 이용
 sharepreferences.Edito editor;(기록을 위한 정의)
 editor=setting.edit()를 이용한다
 순서를 거친다

 */

public class MainActivity extends AppCompatActivity {
    EditText etId, etPw;
    CheckBox autoLogin;

    SharedPreferences setting;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = (EditText)findViewById(R.id.idEt);
        etPw  =(EditText)findViewById(R.id.pwEt);

        autoLogin =(CheckBox)findViewById(R.id.autoCb);

        setting = getSharedPreferences("setting",0);
        editor = setting.edit();


        //자동 로그인을 성정을 했다면 앱이 다시 시작하는 경우에도 입력했던 값이
        //유지되도로록 하기 위한 코드
        if(setting.getBoolean("autoLogin_enable",false)){
            etId.setText(setting.getString("ID",""));
            etPw.setText(setting.getString("PW",""));
            autoLogin.setChecked(true);

        }


        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    String id = etId.getText().toString();
                    String pw = etPw.getText().toString();

                    editor.putString("ID",id);
                    editor.putString("PW",pw);
                    editor.putBoolean("autoLogin_enable",true);
                    editor.commit();//최종 변동값을 저장하기 위해서 반드시 사용해야 한다
                }else{
                    editor.clear();
                    editor.commit();
                }
            }
        });

    }
}
