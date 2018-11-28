package com.zua.cx.coursedesign2application;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/**
 * 章节与题目类型选择
 */
public class ChoosePageActivity extends AppCompatActivity {

    //获取Intent
    public static Intent getIntent(Context context){
        Intent intent = new Intent(context,QuestionActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getSupportFragmentManager();
        Fragment recyclerView = fm.findFragmentById(R.id.choose_page_fragment);
        if(recyclerView==null){
            recyclerView = new RecyclerViewFragment();
            fm.beginTransaction()
                    .add(R.id.choose_page_fragment,recyclerView)
                    .commit();
        }
    }
}
