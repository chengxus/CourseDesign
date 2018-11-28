package com.zua.cx.coursedesign2application.model;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CataLogTable extends QuestionDatabase {
    public CataLogTable(Context context) {
        super(context);
    }
    @Override
    public void initTable() {
        setTable("catalog");
    }

    public List<String> getMapByType(String type){
        List<String> list =new ArrayList<>();
        Cursor cursor=select("type=" + type);
        while( cursor.moveToNext( ) ){
            list.add( cursor.getString(2));
        }
        return list;
    }
}
