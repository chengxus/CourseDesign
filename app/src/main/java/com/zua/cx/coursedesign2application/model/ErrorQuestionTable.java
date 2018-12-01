package com.zua.cx.coursedesign2application.model;

import android.content.ContentValues;
import android.content.Context;

import java.util.List;

public class ErrorQuestionTable extends UserDatabase {

    public ErrorQuestionTable(Context context) {
        super(context);
    }

    @Override
    public void initTable() {
        setTable("error_question");
    }
    //创建values
    public ContentValues changeToValue(List<String> value){
        ContentValues values = new ContentValues();
        values.put(UserDataBaseHelper.ErrorQuestionTable.ErrorQuestion.QUESTIONID,value.get(0));
        values.put(UserDataBaseHelper.ErrorQuestionTable.ErrorQuestion.QUESTIONTYPE,value.get(1));
        values.put(UserDataBaseHelper.ErrorQuestionTable.ErrorQuestion.DATETIME,value.get(2));
        return values;
    }
    //插入新数据
    public long insertList(List<String> value){
        return mDatabase.insert("error_question",null,changeToValue(value));
    }
    //修改数据
    public long updateList(String key,String value,String questionID){
        ContentValues values = new ContentValues();
        values.put(key,value);
        return mDatabase.update("error_question",values,
                UserDataBaseHelper.QuestionLogTable.QuestionLog.QUESTIONID+"=?",
                new String[]{questionID});
    }
}
