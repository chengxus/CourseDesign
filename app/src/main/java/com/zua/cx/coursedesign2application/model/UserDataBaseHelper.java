package com.zua.cx.coursedesign2application.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 本数据库用于存储用户信息
 */

public class UserDataBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "userdata.db";

    public UserDataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+QuestionLogTable.NAME);
        db.execSQL("create table " + RecordTable.NAME);
        db.execSQL("create table "+ ErrorQuestionTable.NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //保存正在做的题目
    public static final class QuestionLogTable{
        public static final String NAME = "questionlog";
        public static final class QuestionLog{
            public final String ID = "id";                //表主键
            public final String QUESTIONID = "questionid";//题目ID用于查找
            public final String ANSWER = "answer";        //该题目标准的答案
            public final String STATUS = "status";        //该题目是否已做
            public final String RECORD = "record";        //该题目用户答案
        }
    }
    //保存历史成绩
    public static final class RecordTable{
        public static final String NAME = "record";
        public static final class Record{
            public static final String ID = "id";                       //主键id
            public static final String QUESTIONTYPE = "questiontype";   //题目类型科目1或4
            public static final String SCORE = "score";                 //分数
            public static final String DATETIME = "datetime";           //做题时间
        }
    }
    //保存收藏的题目
    public static final class CollectionTable{
        public static final String NAME = "collectiontable";
        public static final class Collection{
            public static final String ID = "id";                   //主键ID
            public static final String QUESTIONID = "questionid";   //题目id
        }
    }
    //保存错题
    public static final class ErrorQuestionTable{
        public static final String NAME = "errorquestion";
        public static final class ErrorQuestion{
            public static final String ID = "id";                       //主键id
            public static final String QUESTIONID = "questionid";       //题目id
            public static final String QUESTIONTYPE = "questiontype";   //题目类型
        }
    }
}
