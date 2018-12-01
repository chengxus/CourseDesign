package com.zua.cx.coursedesign2application.model;

import android.content.ContentValues;
import android.content.Context;

import java.util.List;

public class CollectionTable extends UserDatabase {

    public CollectionTable(Context context) {
        super(context);
    }

    @Override
    public void initTable() {
        setTable("collection_table");
    }
    //创建values
    public ContentValues changeToValue(List<String> value){
        ContentValues values = new ContentValues();
        values.put(UserDataBaseHelper.CollectionTable.Collection.QUESTIONID,value.get(0));
        values.put(UserDataBaseHelper.CollectionTable.Collection.QUESTIONTYPE,value.get(1));
        return values;
    }
    //插入新数据
    public long insertList(List<String> value){
        return mDatabase.insert("collection_table",null,changeToValue(value));
    }
}
