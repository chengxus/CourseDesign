package com.zua.cx.coursedesign2application;

import android.os.Bundle;
import android.provider.MediaStore;
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
 * 判断题
 */
public class QuestionJudgeFragment extends Fragment {

    private RadioGroup radioGroup;
    private TextView textView;
    private RadioButton radioButton1;
    private RadioButton radioButton2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view       = inflater.inflate(R.layout.fragment_judge_question,container,false);
        textView        = (TextView)view.findViewById(R.id.judge_text);
        radioGroup      = (RadioGroup) view.findViewById(R.id.radio_group);
        radioButton1    = (RadioButton) view.findViewById(R.id.judge_radio_button1);
        radioButton2    = (RadioButton) view.findViewById(R.id.judge_radio_button2);

        radioButton1.setText("正确");
        radioButton2.setText("错误");

        QuestionData questionData = null;
        try{
            questionData= QuestionData.getQuestionData();
        }catch (Exception e){
            e.printStackTrace();
        }
        //是否已查看解析
        if(questionData.lookTheExplain){
            radioButton1.setEnabled(false);
            radioButton2.setEnabled(false);
        }
        if(questionData.multiChoiceAnswer!=0){
            radioButton1.setEnabled(false);
            radioButton2.setEnabled(false);
            //判题
            String text = null;
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
                        case R.id.judge_radio_button1:
                            questionData.multiChoiceAnswer = 1;
                            break;
                        case R.id.judge_radio_button2:
                            questionData.multiChoiceAnswer = 2;
                            break;
                        default:
                            break;
                    }
                    //判题
                    String text = null;
                    if(questionData.checkanswer(questionData.multiChoiceAnswer)){
                        text = "回答正确\n";
                    }else {
                        text = "回答错误\n";
                    }
                    text = text + questionData.currentQuestion().getQuestionExplain();
                    textView.setText(text);
                }
                //屏蔽按钮防止重复回答
                radioButton1.setEnabled(false);
                radioButton2.setEnabled(false);
            }
        });
        return view;
    }
}
