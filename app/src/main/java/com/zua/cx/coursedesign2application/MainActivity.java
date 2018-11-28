package com.zua.cx.coursedesign2application;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zua.cx.coursedesign2application.common.GetRandom;
import com.zua.cx.coursedesign2application.model.QuestionDatabase;
import com.zua.cx.coursedesign2application.model.QuestionTable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    //检测题库是否存在
    public void checkDatabase(){
        String dbPath = "/data/data/com.zua.cx.coursedesign2application/databases/";
        String dbName = "question.db";
        File path = new File(dbPath);
        File file = new File(path,dbName);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        if(!(file.exists())){
            if((!path.exists())){
                path.mkdirs();
            }
           try {
                inputStream = getResources().openRawResource(R.raw.question);
                outputStream = new FileOutputStream(file);
                byte[] buff = new byte[1024];
                int length;
                while( ( length = inputStream.read(buff) ) > 0){
                    outputStream.write(buff,0,length);
                    Log.i("testIO",new Integer(length).toString());
                }
               inputStream.close();
               outputStream.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkDatabase();
        if(1==1){
            Intent intent = HomePageActivity.getIntent(MainActivity.this);
            startActivity(intent);
        }
    }
}
