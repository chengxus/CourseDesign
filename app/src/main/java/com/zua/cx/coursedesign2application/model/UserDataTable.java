package com.zua.cx.coursedesign2application.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

public class UserDataTable extends UserDatabase {
    public UserDataTable(Context context) {
        super(context);
    }

    @Override
    public void initTable() {
        setTable("user_data");
    }
    //创建values
    public ContentValues changeToValue(List<String> value){
        ContentValues values = new ContentValues();
        values.put(UserDataBaseHelper.UserDataTable.UserData.ID,"0");
        values.put(UserDataBaseHelper.UserDataTable.UserData.CURRENTCARPASSID,value.get(0));
        values.put(UserDataBaseHelper.UserDataTable.UserData.DATETIME,value.get(1));
        return values;
    }
    //插入新数据
    public long insertList(List<String> value){
        return mDatabase.insert("user_data",null,changeToValue(value));
    }
    //修改数据
    public long updateList(String key,int value){
        Cursor cursor = select("1=1");
        cursor.moveToNext();
        String str = cursor.getString(0);
        ContentValues values = new ContentValues();
        values.put(key,value);
        return mDatabase.update("user_data",values,"id=?",new String[]{str});
    }
}
