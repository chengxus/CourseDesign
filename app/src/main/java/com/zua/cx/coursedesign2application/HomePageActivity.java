package com.zua.cx.coursedesign2application;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zua.cx.coursedesign2application.model.QuestionData;
import com.zua.cx.coursedesign2application.model.UserDataTable;

/**
 * 提供选择题目类型
 */
public class HomePageActivity extends AppCompatActivity {

    //声明页面Button
    private Button mButton0;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;
    private Button mButton6;
    private Button mButton7;
    private Button mButton8;

    //获取所有按钮
    private void setButton(){
        mButton0 = (Button)findViewById(R.id.exercise_0);
        mButton1 = (Button)findViewById(R.id.exercise_1);
        mButton2 = (Button)findViewById(R.id.exercise_2);
        mButton3 = (Button)findViewById(R.id.exercise_3);
        mButton4 = (Button)findViewById(R.id.exercise_4);
//        mButton5 = (Button)findViewById(R.id.exercise_5);
//        mButton6 = (Button)findViewById(R.id.exercise_6);
//        mButton7 = (Button)findViewById(R.id.exercise_7);
        mButton8 = (Button)findViewById(R.id.exercise_8);
    }

    //设置监听器
    private void setOnClicker(){
        mButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionData questionData = QuestionData.getQuestionData(0,3,HomePageActivity.this);
                questionData.createQuestions(0,carpassid);
                Intent intent = QuestionActivity.getIntent(HomePageActivity.this);
                startActivity(intent);
            }
        });
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionData questionData = QuestionData.getQuestionData(1,3,HomePageActivity.this);
                questionData.createQuestions(1,carpassid);
                Intent intent = QuestionActivity.getIntent(HomePageActivity.this);
                startActivity(intent);
            }
        });
        //章节练习
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionData questionData = QuestionData.getQuestionData(HomePageActivity.this);
                questionData.saveType=2;
                questionData.saveExamType=carpassid;
                Intent intent = ChoosePageActivity.getIntent(HomePageActivity.this);
                startActivity(intent);
            }
        });
        //筛选练习
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionData questionData = QuestionData.getQuestionData(HomePageActivity.this);
                questionData.saveType=3;
                questionData.saveExamType=carpassid;
                Intent intent = ChoosePageActivity.getIntent(HomePageActivity.this);
                startActivity(intent);
            }
        });
        //模拟考试
        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionData questionData = QuestionData.getQuestionData(4,3,HomePageActivity.this);
                questionData.createQuestions(4,carpassid);
                Intent intent = QuestionActivity.getIntent(HomePageActivity.this);
                startActivity(intent);
            }
        });
//        //我的收藏
//        mButton5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        //我的错题
//        mButton6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        //历史成绩
//        mButton7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        //切换考试科目
        mButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDataTable userDataTable = new UserDataTable(HomePageActivity.this);
                Cursor cursor = userDataTable.select("id=0");
                cursor.moveToNext();
                int passid = 0;
                passid = cursor.getInt(1);
                if(passid == 2){
                    userDataTable.updateList("current_pass_id",3);
                }
                if(passid == 3){
                    userDataTable.updateList("current_pass_id",2);
                }
                updata();
            }
        });
    }

    //获取intent
    public static Intent getIntent(Context context){
        Intent intent = new Intent(context,HomePageActivity.class);
        return intent;
    }

    public void updata(){
        QuestionData questionData = QuestionData.getQuestionData(HomePageActivity.this);
        carpassid = questionData.getExamType(HomePageActivity.this);
        if(carpassid==2){
            mButton8.setText("C1 科目一");
        }
        if(carpassid==3){
            mButton8.setText("C1 科目四");
        }
    }
    //carpassid
    private int carpassid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        setButton();
        setOnClicker();
        updata();

//        测试用该部分
//        Log.i("test","运行至此");
//        QuestionData questionData = QuestionData.getQuestionData(HomePageActivity.this);
//        Intent intent = ChoosePageActivity.getIntent(HomePageActivity.this);
//        startActivity(intent);
    }
}
