package com.example.user.android_use1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    Button saveBtn, disPlayBtn, delBtn;
    EditText editText;
    TextView txtDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveBtn =(Button)findViewById(R.id.btnSave);
        disPlayBtn =(Button)findViewById(R.id.btnDisplay);
        delBtn =(Button)findViewById(R.id.btnDel);
        editText = (EditText)findViewById(R.id.et01);
        txtDisplay =(TextView)findViewById(R.id.txtIn);

        saveBtn.setOnClickListener(listener);
        disPlayBtn.setOnClickListener(listener);
        delBtn.setOnClickListener(listener);
    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnSave:
                    FileOutputStream fileOutputStream = null;
                    try {
                        fileOutputStream = openFileOutput("test1.txt", Context.MODE_PRIVATE);
                        String saveData = editText.getText().toString();
                        fileOutputStream.write(saveData.getBytes());
                        Toast.makeText(getApplicationContext(), "저장 완료", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (fileOutputStream != null) fileOutputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                case R.id.btnDisplay:

                    FileInputStream fileInputStream = null;
                    try {
                        fileInputStream = openFileInput("test1.txt");
                       byte[] displayData = new byte[fileInputStream.available()];
                       while (fileInputStream.read(displayData) != -1) {}
                        txtDisplay.setText(new String(displayData));
                        Toast.makeText(getApplicationContext(), "화면 출력 완료", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (fileInputStream != null) fileInputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    break;
                case R.id.btnDel:
                    boolean bool = deleteFile("test1.txt");
                    if(bool){
                        Toast.makeText(getApplicationContext(),"삭제완료!!",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"삭제오류!!",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
