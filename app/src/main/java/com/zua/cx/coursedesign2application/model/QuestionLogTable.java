package com.zua.cx.coursedesign2application.model;

import android.content.ContentValues;
import android.content.Context;

import java.util.List;

public class QuestionLogTable extends UserDatabase {

    public QuestionLogTable(Context context) {
        super(context);
    }

    @Override
    public void initTable() {
        setTable("question_log");
    }
    //创建values
    public ContentValues changeToValue(List<String> value){
        ContentValues values = new ContentValues();
        values.put(UserDataBaseHelper.QuestionLogTable.QuestionLog.QUESTIONID,value.get(0));
        values.put(UserDataBaseHelper.QuestionLogTable.QuestionLog.ANSWER,value.get(1));
        values.put(UserDataBaseHelper.QuestionLogTable.QuestionLog.STATUS,value.get(2));
        values.put(UserDataBaseHelper.QuestionLogTable.QuestionLog.RECORD,value.get(3));
        values.put(UserDataBaseHelper.QuestionLogTable.QuestionLog.LOOKEXPLAIN,value.get(4));
        return values;
    }
    //插入新数据
    public long insertList(List<String> value){
        return mDatabase.insert("question_log",null,changeToValue(value));
    }
    //修改数据
    public long updateList(String key,String value,String questionID){
        ContentValues values = new ContentValues();
        values.put(key,value);
        return mDatabase.update("question_log",values,
                UserDataBaseHelper.QuestionLogTable.QuestionLog.QUESTIONID+"=?",
                new String[]{questionID});
    }
}
