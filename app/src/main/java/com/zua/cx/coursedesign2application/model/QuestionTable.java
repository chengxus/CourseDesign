package com.zua.cx.coursedesign2application.model;

import android.content.Context;

/**
 * 数据库中question表对应的类
 */
public class QuestionTable extends QuestionDatabase {
    public QuestionTable(Context context) {
        super(context);
    }

    @Override
    public void initTable() {
        setTable("question");
    }
}
