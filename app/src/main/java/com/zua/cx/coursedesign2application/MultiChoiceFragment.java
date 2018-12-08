package com.zua.cx.coursedesign2application;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.zua.cx.coursedesign2application.model.QuestionData;

/**
 * 多选题的选项部分
 */
public class MultiChoiceFragment extends Fragment {

    //声明控件
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    //保存按钮
    Button button;
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.multi_choice_input,container,false);
        checkBox1 = (CheckBox)view.findViewById(R.id.question_checkbox1);
        checkBox2 = (CheckBox)view.findViewById(R.id.question_checkbox2);
        checkBox3 = (CheckBox)view.findViewById(R.id.question_checkbox3);
        checkBox4 = (CheckBox)view.findViewById(R.id.question_checkbox4);
        button    = (Button)view.findViewById(R.id.checkbox_button);
        textView  = (TextView)view.findViewById(R.id.multi_text);

        //初始化选项
        QuestionData questionData = null;
        try {
            questionData = QuestionData.getQuestionData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkBox1.setText(questionData.currentQuestion().getOption1());
        checkBox2.setText(questionData.currentQuestion().getOption2());
        checkBox3.setText(questionData.currentQuestion().getOption3());
        checkBox4.setText(questionData.currentQuestion().getOption4());
        //是否查看解析
        if (questionData.lookTheExplain){
            checkBox1.setEnabled(false);
            checkBox2.setEnabled(false);
            checkBox3.setEnabled(false);
            checkBox4.setEnabled(false);
            button.setEnabled(false);
        }
        //是否作答
        if(questionData.multiChoiceAnswer!=0){
            checkBox1.setEnabled(false);
            checkBox2.setEnabled(false);
            checkBox3.setEnabled(false);
            checkBox4.setEnabled(false);
            button.setEnabled(false);
            if(!questionData.lookTheExplain){
                //判题
                String text;
                if(questionData.checkanswer(questionData.multiChoiceAnswer)){
                    text = "回答正确\n";
                }else {
                    text = "回答错误\n";
                }
                text = text + questionData.currentQuestion().getQuestionExplain();
                textView.setText(text);
            }
        }
        //设置监听器
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //第4位置1
                    QuestionData.multiChoiceAnswer |= (1 << 3);
                }else{
                    //第4位置0
                    QuestionData.multiChoiceAnswer &= ~(1 << 3);
                }
            }
        });
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //第3位置1
                    QuestionData.multiChoiceAnswer |= (1 << 2);
                }else{
                    //第3位置0
                    QuestionData.multiChoiceAnswer &= ~(1 << 2);
                }
            }
        });
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //第2位置1
                    QuestionData.multiChoiceAnswer |= (1 << 1);
                }else{
                    //第2位置0
                    QuestionData.multiChoiceAnswer &= ~(1 << 1);
                }
            }
        });
        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //第1位置1
                    QuestionData.multiChoiceAnswer |= 1;
                }else{
                    //第1位置0
                    QuestionData.multiChoiceAnswer &= ~1;
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionData questionData = null;
                try {
                    questionData=QuestionData.getQuestionData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(!questionData.lookTheExplain){
                    //判题
                    String text;
                    if(questionData.checkanswer(questionData.multiChoiceAnswer)){
                        text = "回答正确\n";
                    }else {
                        text = "回答错误\n";
                    }
                    text = text + questionData.currentQuestion().getQuestionExplain();
                    textView.setText(text);
                }
                //禁用按钮
                checkBox1.setEnabled(false);
                checkBox2.setEnabled(false);
                checkBox3.setEnabled(false);
                checkBox4.setEnabled(false);
                button.setEnabled(false);
            }
        });
        return view;
    }
}
