package com.example.xmlparsing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by user on 2017-03-28.
 */

public class Main2Activity extends AppCompatActivity{

        ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        ArrayList<Student> list = parser();
        String[] data = new String[list.size()];

        for(int i= 0; i<list.size();i++){
            data[i] = list.get(i).getName()+" "+list.get(i).getAge()
                    +" "+list.get(i).getAddress();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, data);

        listView.setAdapter(adapter);
    }

    private ArrayList<Student> parser() {

        ArrayList<Student> arrayList = new ArrayList<Student>();
        InputStream inputStream = getResources().openRawResource(R.raw.student);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        //   BufferedReader reader = new BufferedReader(inputStreamReader);

        XmlPullParserFactory factory = null;
        XmlPullParser xmlParser = null;


        try {
            factory = XmlPullParserFactory.newInstance();
            xmlParser = factory.newPullParser();

            xmlParser.setInput(inputStreamReader);
            int eventType = xmlParser.getEventType();
            Student student = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String startTag = xmlParser.getName();
                        if (startTag.equals("student")) {
                            student = new Student();
                        }
                        if (startTag.equals("name")) {
                            student.setName(xmlParser.nextText());
                        }
                        if(startTag.equals("age")){
                            student.setAge(xmlParser.nextText());
                        }
                        if(startTag.equals("address")){
                            student.setAddress(xmlParser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String endTag = xmlParser.getName();
                        if (endTag.equals("student")) {
                            arrayList.add(student);
                        }

                        break;
                }
                eventType = xmlParser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            //    if (inputStreamReader != null) inputStreamReader.close();
                if (inputStream != null) inputStream.close();

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return  arrayList;

    }
}


