package com.zua.cx.coursedesign2application.model;

import android.content.ContentValues;
import android.content.Context;

import java.util.List;

public class RecordTable extends UserDatabase {

    public RecordTable(Context context) {
        super(context);
    }
    @Override
    public void initTable() {
        setTable("record");
    }
    //创建values
    public ContentValues changeToValue(List<String> value){
        ContentValues values = new ContentValues();
        values.put(UserDataBaseHelper.RecordTable.Record.QUESTIONTYPE,value.get(0));
        values.put(UserDataBaseHelper.RecordTable.Record.SCORE,value.get(1));
        values.put(UserDataBaseHelper.RecordTable.Record.DATETIME,value.get(2));
        return values;
    }
    //插入新数据
    public long insertList(List<String> value){
        return mDatabase.insert("record",null,changeToValue(value));
    }
}
