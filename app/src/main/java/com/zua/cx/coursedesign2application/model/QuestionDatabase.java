package com.zua.cx.coursedesign2application.model;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * 对数据库访问基本操作的抽象
 */
public abstract class QuestionDatabase{

    private Context mContext;
    private String table;
    private SQLiteDatabase mDatabase;

    public QuestionDatabase(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new QuestionBaseHelper(mContext).getWritableDatabase();
        initTable();
    }

    public abstract void initTable();

    public void setTable(String table) {
        this.table = table;
    }

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
            throw new SQLException("表名不能为空");
        }

    }
    public Cursor selectByID(int id){
        String where = " id="+id;
        return select(where);
    }

    public int selectCount(String where) throws SQLException{
        if(table!=null){
            Cursor cursor = mDatabase.query(table,
                    new String[]{" count(*) "},
                    where,
                    null,
                    null,
                    null,
                    null
            );
            cursor.moveToNext();
            return cursor.getInt(0);
        }else{
            throw new SQLException("表名不能为空");
        }

    }
}
