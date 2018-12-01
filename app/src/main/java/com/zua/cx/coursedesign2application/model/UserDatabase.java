package com.zua.cx.coursedesign2application.model;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public abstract class UserDatabase {
    public Context mContext;
    private String table;
    public SQLiteDatabase mDatabase;

    public UserDatabase(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new UserDataBaseHelper(mContext).getWritableDatabase();
        initTable();
    }

    public abstract void initTable();

    public void setTable(String table) {
        this.table = table;
    }

    //通过where查询
    public Cursor select(String where){
        if(table!=null){
            Cursor cursor = mDatabase.query(table,
                    null,
                    where,
                    null,
                    null,
                    null,
                    null
            );
            return cursor;
        }else{
            new SQLException("请表名不能为空");
            return null;
        }

    }
    //通过id查询数据
    public Cursor selectByID(int id){
        String where = "id="+id;
        return select(where);
    }

    //查询满足条件的总数
    public int selectCount(String where) throws SQLException{
        if(table!=null){
            Cursor cursor = mDatabase.query(table,
                    new String[]{"count(*)"},
                    where,
                    null,
                    null,
                    null,
                    null
            );
            int count = 0;
            while(cursor.moveToNext()){
                count++ ;
            }
            return count;
        }else{
            throw new SQLException("请表名不能为空");
        }
    }
    //插入新记录

}
