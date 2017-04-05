package com.example.fileresource;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView displaytxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button =(Button )findViewById(R.id.btnDisplay);
        displaytxt =(TextView)findViewById(R.id.txtIn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream inputStream = null;
                try{
                    inputStream = getResources().openRawResource(R.raw.test);
                    byte[] displayData = new byte[inputStream.available()];
                    while (inputStream.read(displayData)!= -1){}
                    displaytxt.setText(new String(displayData));

                }catch (Exception e){

                    e.printStackTrace();

                }finally {

                    try {

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        });
    }
}
