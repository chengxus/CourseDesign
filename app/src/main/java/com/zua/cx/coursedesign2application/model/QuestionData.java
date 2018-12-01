package com.zua.cx.coursedesign2application.model;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;

import com.zua.cx.coursedesign2application.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 封装model层对外的接口主要对题目提供数据
 */
public class QuestionData {

    //保存答案
    public static int multiChoiceAnswer = 0;
    //用户是否看解析
    public static boolean lookTheExplain = false;
    //传输数据
    public static int saveType;
    public static int saveExamType;

    private static QuestionData questionData = null;
    //存储当前题目索引
    private int index = 0;
    //保存生成的题目序列
    private List<String> questions;
    public Context context = null;

    //通过题目类型生成题目type表示题目类型，questionType表示考试类型,context用于实例化数据库连接，id表示用于分类的id
    public void createQuestions(int type, int examType, int id){
        saveType = type ;
        index = 0;
        questions   = new ArrayList<>();
        QuestionTable questionTable = new QuestionTable(context);
        Cursor cursor = null;
        switch (type){
            case 2:
                cursor = questionTable.select(" carpassid="+examType+" AND chapterid="+id);
                break;
            case 3:
                cursor = questionTable.select(" carpassid="+examType+" AND selectid="+id);
                break;
            default:
                break;
        }
        while(cursor.moveToNext()){
            questions.add(cursor.getString(0));
        }
    }

    //创建题目列表type表示题目类型，questionType表示考试类型,context用于实例化数据库连接
    public void createQuestions(int type, int examType){
        saveType = type;
        index = 0;
        QuestionTable questionTable = new QuestionTable(context);
        Cursor cursor = null;
        switch (type){
            case 0:                              //顺序练习
                questions = new ArrayList<>();
                cursor = questionTable.select("carpassid="+examType);
                while(cursor.moveToNext()){
                    questions.add(cursor.getString(0));
                }
                break;
            case 1:                                 //随机练习
                questions = new ArrayList<>();
                cursor = questionTable.select("carpassid="+examType);
                while(cursor.moveToNext()){
                    questions.add(cursor.getString(0));
                }
                Collections.shuffle(questions,new Random(System.currentTimeMillis()));//打乱数组顺序
                break;
            case 4:                       //模拟考试
                if(examType == 2){             //科目1
                    questions = new ArrayList<String>();
                    List<String> temp= new ArrayList<>();
                    cursor = questionTable.select("carpassid="+examType);
                    while(cursor.moveToNext()){
                        temp.add(cursor.getString(0));
                    }
                    Collections.shuffle(temp,new Random(System.currentTimeMillis()));//打乱数组顺序
                    for(int i=0;i<100;i++){
                        questions.add(temp.get(i));
                    }
                }else{
                    questions = new ArrayList<String>();
                    ArrayList<String> temp = new ArrayList<>();
                    cursor = questionTable.select("carpassid="+examType+
                            " AND "+"type=0"); //筛选判断题
                    while(cursor.moveToNext()){
                        temp.add(cursor.getString(0));
                    }
                    Collections.shuffle(temp,new Random(System.currentTimeMillis()));
                    for(int i=0;i<20;i++){
                        questions.add(temp.get(i));
                    }
                    cursor = questionTable.select("carpassid="+examType+
                            " AND "+"type=1"); //筛选单选题
                    temp = new ArrayList<String>();
                    while(cursor.moveToNext()){
                        temp.add(cursor.getString(0));
                    }
                    Collections.shuffle(temp,new Random(System.currentTimeMillis()));
                    for(int i=0;i<20;i++){
                        questions.add(temp.get(i));
                    }
                    cursor = questionTable.select(" carpassid="+examType+
                            " AND "+"type=2"); //筛选多选题
                    temp = new ArrayList<String>();
                    while(cursor.moveToNext()){
                        temp.add(cursor.getString(0));
                    }
                    Collections.shuffle(temp,new Random(System.currentTimeMillis()));
                    for(int i=0;i<10;i++){
                        questions.add(temp.get(i));
                    }
                }
                break;
            default:
                break;
        }
    }

    private QuestionData(Context context){
        this.context = context;
    }

    public static QuestionData getQuestionData(int type, int examType,Context context, int id){
        if (questionData == null){
            questionData = new QuestionData(context);
            questionData.createQuestions(type,examType,id);
        }
        return questionData;
    }
    public static QuestionData getQuestionData(int type, int examType, Context context){
        if (questionData == null){
            questionData = new QuestionData(context);
            questionData.createQuestions(type,examType);
        }
        return questionData;
    }
    public static QuestionData getQuestionData(Context context){
        if (questionData == null){
            questionData = new QuestionData(context);
        }
        return questionData;
    }
    public static QuestionData getQuestionData() throws Exception{
        if (questionData == null){
            throw new Exception("该单例未初始化");
        }
        return questionData;
    }

    /**
     * 对外提供的操作部分
     *
     */
    //检查答案是否正确
    public boolean checkanswer(int userAnswer){
        int answer = currentQuestion().getQuestionAnswer();
        return answer == userAnswer;
    }
    //获取当前题目
    public Question currentQuestion(){
        QuestionTable questionTable = new QuestionTable(context);
        Cursor cursor = questionTable.selectByID(Integer.parseInt(questions.get(index)));
        Question question = null;
        if(cursor.moveToNext()){
            question = new Question()
                    .setQuestionID(cursor.getString(0))         //题目id
                    .setQuestionProblem(cursor.getString(1))    //题目题干
                    .setQuestionAnswer(cursor.getInt(2))        //题目标准答案
                    .setQuestionType(cursor.getInt(6))          //题目类型
                    .setQuestionExplain(cursor.getString(15))   //题目解释
                    .setOption1(cursor.getString(7))            //题目选项
                    .setOption2(cursor.getString(8))
                    .setOption3(cursor.getString(9))
                    .setOption4(cursor.getString(10));
        }
        return question;
    }
    //下一题
    public Question nextQuestion(){
        index = (index + 1) % questions.size();
        multiChoiceAnswer = 0;
        //需写入数据库
        lookTheExplain = false;

        return currentQuestion();
    }
    //上一题
    public Question lastQuestion(){
        index = (index + questions.size() - 1) % questions.size();
        multiChoiceAnswer = 0;
        //需写入数据库
        lookTheExplain = false;

        return currentQuestion();
    }
    //获取当前题目索引
    public int getCurrentIndex() {
        return index;
    }
    //获取当前题目总数
    public int getQuestionsLength(){
        return questions.size();
    }
    //获取题目分类与单元
    public List<String> getQuestionTypeByType(String type){
        CataLogTable cataLogTable = new CataLogTable(context);
        List<String> typeList;
        if(saveType==2){
            typeList=cataLogTable.getListByType("chapter");
        }else{
            typeList=cataLogTable.getListByType("select");
        }
        return typeList;
    }
    //通过name获取id
    public int getIDByName(String name){
        CataLogTable cataLogTable = new CataLogTable(context);
        int type=cataLogTable.getIDByName(name);
        return type;
    }
    //修改题库
    public boolean changeCarPassID(int id){
        UserDataTable userDataTable = new UserDataTable(context);
        long temp;
        if(userDataTable.selectCount("1=1")>0){
             temp= userDataTable.updateList("current_pass_id",new Integer(id).toString());
        }else {
            List<String> list = new ArrayList<>();
            list.add(new Integer(id).toString());
            list.add("0");
            temp = userDataTable.insertList(list);
        }
        return temp > 0;
    }
    //获取当前题库类型
    public int getExamType(Context context){
        UserDataTable userDataTable = new UserDataTable(context);
        Cursor cursor = userDataTable.selectByID(0);
        int examType = 0;
        if(cursor.moveToNext()){
             examType= cursor.getInt(1);
        }
        saveExamType = examType;
        return examType;
    }
}
