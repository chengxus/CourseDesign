package com.zua.cx.coursedesign2application.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zua.cx.coursedesign2application.RecyclerViewFragment;

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
        //当前题目
        db.execSQL("create table "+QuestionLogTable.NAME+"("+
                "id integer primary key autoincrement , "+
                QuestionLogTable.QuestionLog.QUESTIONID+" , "+
                QuestionLogTable.QuestionLog.ANSWER+" , "+
                QuestionLogTable.QuestionLog.STATUS+" , "+
                QuestionLogTable.QuestionLog.RECORD+" , "+
                QuestionLogTable.QuestionLog.LOOKEXPLAIN+
                ")"
        );
        //历史成绩
        db.execSQL("create table " + RecordTable.NAME+"("+
                "id integer primary key autoincrement , "+
                RecordTable.Record.QUESTIONTYPE+","+
                RecordTable.Record.SCORE+","+
                RecordTable.Record.DATETIME+
                ")"
        );
        //收藏的题目
        db.execSQL("create table "+CollectionTable.NAME+"("+
                "question_id integer primary key , "+
                CollectionTable.Collection.QUESTIONTYPE+
                ")"
        );
        //错题
        db.execSQL("create table "+ ErrorQuestionTable.NAME+"("+
                "question_id integer primary key , "+
                ErrorQuestionTable.ErrorQuestion.QUESTIONTYPE+" , "+
                ErrorQuestionTable.ErrorQuestion.DATETIME+
                ")"
        );
        //用户信息
        db.execSQL("CREATE TABLE "+UserDataTable.NAME+"("+
                " id integer primary key ,"+
                UserDataTable.UserData.CURRENTCARPASSID+" , "+
                UserDataTable.UserData.DATETIME+
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //保存正在做的题目
    public static final class QuestionLogTable{
        public static final String NAME = "question_log";
        public static final class QuestionLog{
            public static final String ID = "id";                //表主键
            public static final String QUESTIONID = "question_id";//题目ID用于查找
            public static final String ANSWER = "answer";        //该题目标准的答案
            public static final String STATUS = "status";        //该题目是否已做
            public static final String RECORD = "record";        //该题目用户答案
            public static final String LOOKEXPLAIN = "look_explain"; //是否查看解析
        }
    }
    //保存历史成绩
    public static final class RecordTable{
        public static final String NAME = "record";
        public static final class Record{
            public static final String ID = "id";                       //主键id
            public static final String QUESTIONTYPE = "question_type";   //题目类型科目1或4
            public static final String SCORE = "score";                 //分数
            public static final String DATETIME = "datetime";           //做题时间
        }
    }
    //保存收藏的题目
    public static final class CollectionTable{
        public static final String NAME = "collection_table";
        public static final class Collection{
            public static final String QUESTIONID = "question_id";   //题目id
            public static final String QUESTIONTYPE = "question_type";//题目类型科目1或4
        }
    }
    //保存错题
    public static final class ErrorQuestionTable{
        public static final String NAME = "error_question";
        public static final class ErrorQuestion{
            public static final String QUESTIONID = "question_id";       //题目id
            public static final String QUESTIONTYPE = "question_type";   //题目类型
            public static final String DATETIME = "date_time";           //错误时间
        }
    }
    //用户信息
    public static final class UserDataTable{
        public static final String NAME = "user_data";
        public static final class UserData{
            public static final String ID = "id";               //id
            public static final String CURRENTCARPASSID = "current_pass_id";//当前考试类型id
            public static final String DATETIME = "date_time";              //已做时间
        }
    }
}
