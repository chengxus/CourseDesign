package com.zua.cx.coursedesign2application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zua.cx.coursedesign2application.model.Question;
import com.zua.cx.coursedesign2application.model.QuestionData;

/**
 * 项目第一次启动时的初始化
 */
public class InitSettingActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_setting);

        radioGroup      = findViewById(R.id.init_radio_group);
        radioButton1    = findViewById(R.id.init_radio_button1);
        radioButton2    = findViewById(R.id.init_radio_button2);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                QuestionData questionData= QuestionData.getQuestionData(InitSettingActivity.this);
                int type = 0;
                switch (checkedId){
                    case R.id.init_radio_button1:
                        type = 2;
                        break;
                    case R.id.init_radio_button2:
                        type = 3;
                        break;
                    default:
                        break;
                }
                questionData.saveExamType = type;
                questionData.changeCarPassID(type);
                Intent intent = HomePageActivity.getIntent(InitSettingActivity.this);
                startActivity(intent);
            }
        });
    }
}
