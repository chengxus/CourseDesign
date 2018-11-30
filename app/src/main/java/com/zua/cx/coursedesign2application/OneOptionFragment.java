package com.zua.cx.coursedesign2application;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zua.cx.coursedesign2application.model.QuestionData;

/**
 * 单选部分fragment
 */
public class OneOptionFragment extends Fragment {
    //声明组件引用
    private RadioGroup radioGroup;
    private TextView textView;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view       = inflater.inflate(R.layout.fragment_one_option,container,false);
        textView        = (TextView) view.findViewById(R.id.one_option_text);
        radioGroup      = (RadioGroup) view.findViewById(R.id.radio_group);
        radioButton1    = (RadioButton)view.findViewById(R.id.radio_button1);
        radioButton2    = (RadioButton)view.findViewById(R.id.radio_button2);
        radioButton3    = (RadioButton)view.findViewById(R.id.radio_button3);
        radioButton4    = (RadioButton)view.findViewById(R.id.radio_button4);

        QuestionData questionData = null;
        try {
             questionData= QuestionData.getQuestionData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        radioButton1.setText(questionData.currentQuestion().getOption1());
        radioButton2.setText(questionData.currentQuestion().getOption2());
        radioButton3.setText(questionData.currentQuestion().getOption3());
        radioButton4.setText(questionData.currentQuestion().getOption4());
        //若已查看解析
        if(questionData.lookTheExplain){
            radioButton1.setEnabled(false);
            radioButton2.setEnabled(false);
            radioButton3.setEnabled(false);
            radioButton4.setEnabled(false);
        }
        //若已作答
        if(questionData.multiChoiceAnswer!=0){
            radioButton1.setEnabled(false);
            radioButton2.setEnabled(false);
            radioButton3.setEnabled(false);
            radioButton4.setEnabled(false);
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
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                QuestionData questionData = null;
                try{
                    questionData= QuestionData.getQuestionData();
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(!questionData.lookTheExplain){
                    switch (checkedId){
                        case R.id.radio_button1:
                            questionData.multiChoiceAnswer = 1;
                            break;
                        case R.id.radio_button2:
                            questionData.multiChoiceAnswer = 2;
                            break;
                        case R.id.radio_button3:
                            questionData.multiChoiceAnswer = 3;
                            break;
                        case R.id.radio_button4:
                            questionData.multiChoiceAnswer = 4;
                            break;
                        default:
                            break;
                    }
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
                radioButton1.setEnabled(false);
                radioButton2.setEnabled(false);
                radioButton3.setEnabled(false);
                radioButton4.setEnabled(false);
            }
        });
        return view;
    }
}
