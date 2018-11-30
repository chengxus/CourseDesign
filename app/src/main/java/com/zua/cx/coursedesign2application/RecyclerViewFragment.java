package com.zua.cx.coursedesign2application;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zua.cx.coursedesign2application.model.QuestionData;

import java.util.List;

public class RecyclerViewFragment extends Fragment {
    private TypeAdapter mAdapter;
    private RecyclerView mTypeRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_for_choose_page,container,false);
        mTypeRecyclerView = (RecyclerView) view.findViewById(R.id.recycer_view);
        mTypeRecyclerView.setLayoutManager(new LinearLayoutManager ( getActivity() ) );
        updateUI();
        return view;
    }
    private void updateUI(){
        QuestionData questionData = null;
        try {
            questionData = QuestionData.getQuestionData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List typeList = questionData.getQuestionTypeByType("select");
        mAdapter = new TypeAdapter(typeList);
        mTypeRecyclerView.setAdapter(mAdapter);
    }

    private class TypeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView = null;
        //题目id
        private int id;

        public TypeHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_option_for_type_chapter,parent,false));
            textView        = itemView.findViewById(R.id.type_and_chapte_button);
            itemView.setOnClickListener(this);
        }

        public void bind(String str){
            textView.setText(str);
            QuestionData questionData = null;
            try{
                questionData = QuestionData.getQuestionData();
            }catch (Exception e){
                e.printStackTrace();
            }
            id = questionData.getIDByName(str);
        }

        @Override
        public void onClick(View v) {
            QuestionData questionData = null;
            try{
                questionData = QuestionData.getQuestionData();
            }catch (Exception e){
                e.printStackTrace();
            }
            //创建题目
            questionData.createQuestions(questionData.saveType,questionData.saveExamType,id);
            Toast.makeText(getActivity(),
                    id + " clicked!", Toast.LENGTH_SHORT)
                    .show();
        }
    }
    private class TypeAdapter extends RecyclerView.Adapter<TypeHolder>{
        private List<String> typeList = null;
        //构造方法
        public TypeAdapter(List<String> typeList){
            this.typeList = typeList;
        }
        @NonNull
        @Override
        public TypeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TypeHolder(layoutInflater,viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull TypeHolder typeHolder, int i) {
            String str = typeList.get(i);
            typeHolder.bind(str);
        }
        //获取RecyclerView的长度
        @Override
        public int getItemCount() {
            int x = typeList.size();
            return typeList.size();
        }
    }
}
