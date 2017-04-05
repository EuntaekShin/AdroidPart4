package com.example.tokenparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

    /*
    파씽이란? html이나 Token, Json에서 태그를 제외한 내가 필요한 자료만 가져와서
    내입맛에 맞게 끌어다 쓰는것

    1.Token Parsing(공백, 구분자에 의해 잘린 하나하나를 Token이라고 한다)
    -String tokenizer
    2. 주요메서드
    -hasMoreElements(다음 토큰이 있는지 확인)
    -nextToken(다음 토큰을 가져오는것)




     */

public class MainActivity extends AppCompatActivity {

    final static String TAG = "Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parser();
    }
    private void parser(){
        Log.i(TAG,"parser()");

        InputStream inputStream = getResources().openRawResource(R.raw.member);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader buff   = new BufferedReader(inputStreamReader);

        StringBuffer stringBuffer = new StringBuffer();
        String line = null;


        try{
            while((line = buff.readLine()) != null){
                stringBuffer.append(line);
            }
            Log.i(TAG,"stringBuffer : " +stringBuffer.toString());

            String str = stringBuffer.toString();
            StringTokenizer stringTokenize = new StringTokenizer(str, "/");
            Log.i(TAG,"Token count : " +stringTokenize.countTokens());
            while (stringTokenize.hasMoreElements()){
                String tokenstr = stringTokenize.nextToken();
                Log.i(TAG,"Token : " + tokenstr);

                StringTokenizer stringTokenizer2 = new StringTokenizer(tokenstr,"/");
                Log.i(TAG,"Token count :" +stringTokenizer2.countTokens());
                while(stringTokenizer2.hasMoreElements()){
                    String tokenstr2 = stringTokenize.nextToken();
                    Log.i(TAG,"Token : " + tokenstr2);
                }
                Log.i(TAG,"--------------------");
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(buff != null)buff.close();
                if(inputStreamReader != null) inputStreamReader.close();
                if(inputStream != null) inputStream.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }

    }
}
