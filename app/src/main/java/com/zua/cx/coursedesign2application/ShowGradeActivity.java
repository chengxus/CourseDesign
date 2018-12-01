package com.zua.cx.coursedesign2application;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.annotation.ColorLong;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowGradeActivity extends AppCompatActivity {

    private TextView textView;

    private static final String INTENT = "com.zua.cx.showgradeactivity";

    public static Intent getIntent(Context context,int grade) {
        Intent intent = new Intent(context,ShowGradeActivity.class);
        intent.putExtra(INTENT,grade);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_grade);
        textView = findViewById(R.id.show_grade_text);
        int temp = getIntent().getIntExtra(INTENT,0);
        textView.setText(new Integer(temp).toString());
        if(temp>=90){
            textView.setTextColor(this.getResources().getColor(R.color.green_text));
        }else{
            textView.setTextColor(this.getResources().getColor(R.color.red_text));
        }
    }
}
