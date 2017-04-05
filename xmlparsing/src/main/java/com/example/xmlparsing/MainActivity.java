package com.example.xmlparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    final static String TAG= "Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parser();
    }

    private  void parser(){
        Log.i(TAG,"parser()");

        InputStream inputStream = getResources().openRawResource(R.raw.student);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        StringBuffer stringBuffer = new StringBuffer();
        String line  = null;

        XmlPullParserFactory factory = null;
        XmlPullParser xmlParser = null;


        try{
            factory = XmlPullParserFactory.newInstance();
            xmlParser = factory.newPullParser();

            xmlParser.setInput(reader);

           int eventType = xmlParser.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        Log.i(TAG,"xml Start");
                        break;
                    case XmlPullParser.START_TAG:
                        Log.i(TAG,"Start TAG :"+ xmlParser.getName());
                        if(xmlParser.getName().equals("student")){
                            int cnt = xmlParser.getAttributeCount();
                            for(int i =0 ; i<cnt ; i++){
                                Log.i(TAG,"Start TAG Name("+i+"):"
                                        + xmlParser.getAttributeName(i));
                                Log.i(TAG,"Start TAG Value("+i+"): "
                                        +xmlParser.getAttributeValue(i));
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        Log.i(TAG,"End TAG : "+xmlParser.getName());
                        break;
                    case XmlPullParser.TEXT:
                        Log.i(TAG,"TEXT : "+xmlParser.getText());
                        break;
                }
                eventType = xmlParser.next();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(reader !=null) reader.close();
                if(inputStreamReader !=null) inputStreamReader.close();
                if(inputStream !=null) inputStream.close();

            }catch (Exception e2){
                e2.printStackTrace();
            }
        }


    }
}
