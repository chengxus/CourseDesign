package com.zua.cx.coursedesign2application;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.lang.Exception;

import com.zua.cx.coursedesign2application.model.Question;
import com.zua.cx.coursedesign2application.model.QuestionData;

/**
 * 所有答题页面由该类控制
 */
public class QuestionActivity extends AppCompatActivity {
    //获取题目数据
    QuestionData questionData;


    //声明页面组件
    private FrameLayout fragmentImg;
    private TextView questionText;
    private TextView questionExplain;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button saveButton;
    //绑定组件
    private void setView(){
        fragmentImg     = (FrameLayout) findViewById(R.id.question_img);
        questionText    = (TextView)    findViewById(R.id.question_problem);
        questionExplain = (TextView)    findViewById(R.id.question_explain);
        //上一题
        button1         = (Button)      findViewById(R.id.table_button1);
        //收藏
        button2         = (Button)      findViewById(R.id.table_button2);
        //显示进度
        button3         = (Button)      findViewById(R.id.table_button3);
        //查看答案
        button4         = (Button)      findViewById(R.id.table_button4);
        //下一题
        button5         = (Button)      findViewById(R.id.table_button5);
        //交卷
        saveButton      = (Button)      findViewById(R.id.question_save_button);
    }
    //绑定监听器
    private void setClick(){
        button1.setOnClickListener(new View.OnClickListener() {   //上一题
            @Override
            public void onClick(View v) {
                //未完
                Question question = questionData.lastQuestion();
                updateQuestion();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {     //收藏
            @Override
            public void onClick(View v) {

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {     //查看解析
            @Override
            public void onClick(View v) {
                String text =questionData.currentQuestion().getQuestionExplain();
                questionExplain.setText(text);
                questionData.lookTheExplain = true;
                updateQuestion();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {     //下一题
            @Override
            public void onClick(View v) {
                Question question = questionData.nextQuestion();
                updateQuestion();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ShowGradeActivity.getIntent(QuestionActivity.this,100);
                startActivity(intent);
            }
        });
    }
    //更新题目信息
    public void updateQuestion(){
        String problem = questionData.currentQuestion().getQuestionProblem();
        questionText.setText(problem);
        int type = questionData.currentQuestion().getQuestionType();
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragmentOption = fm.findFragmentById(R.id.question_option);
        switch(type){
            //判断
            case 0:
                fragmentOption = new QuestionJudgeFragment();
                fm.beginTransaction()
                        .replace(R.id.question_option,fragmentOption)
                        .commit();
                break;
            //单选题
            case 1:
                fragmentOption = new OneOptionFragment();
                fm.beginTransaction()
                        .replace(R.id.question_option,fragmentOption)
                        .commit();
                break;
            //多选题
            case 2:
                fragmentOption = new MultiChoiceFragment();
                fm.beginTransaction()
                        .replace(R.id.question_option,fragmentOption)
                        .commit();
                break;
            default:
                break;
        }
        String currentQuestion =(questionData.getCurrentIndex())+"/"+(questionData.getQuestionsLength());
        button3.setText(currentQuestion);
        //若本题已查看解析
        if(questionData.lookTheExplain){
            questionExplain.setText(questionData.currentQuestion().getQuestionExplain());
        }else{
            questionExplain.setText("");
        }
        if(questionData.saveType!=4){
            //View.INVISIBLE可以隐藏但按钮存在不影响布局View.GONE，去掉该控件影响布局
            saveButton.setVisibility(View.INVISIBLE);
        }
    }
    //获取intent
    public static Intent getIntent(Context context){
        Intent intent = new Intent(context,QuestionActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        try {
            questionData = QuestionData.getQuestionData();
        }catch (Exception e){
            e.printStackTrace();
        }
        setView();
        setClick();
        updateQuestion();
    }
}
