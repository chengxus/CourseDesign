package com.zua.cx.coursedesign2application.model;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class CataLogTable extends QuestionDatabase {
    public CataLogTable(Context context) {
        super(context);
    }
    @Override
    public void initTable() {
        setTable("catalog");
    }


    public List<String> getListByType(String type){
        List<String> list =new ArrayList<>();
        Cursor cursor=select("type='" + type+"'");
        while( cursor.moveToNext( ) ){
            list.add( cursor.getString(2));
        }
        return list;
    }
    public int getIDByName(String typeName){
        Cursor cursor=select("name='" + typeName+"'");
        cursor.moveToNext( );
        int id = cursor.getInt(0);
        return id;
    }
}
