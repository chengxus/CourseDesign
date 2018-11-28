package com.zua.cx.coursedesign2application.model;

/**
 * 对题目信息的抽象，对外提供访问题目的方法
 */
public class Question {
    //题目在数据库ID
    private String questionID;
    //题目题干
    private String questionProblem;
    //保存选项
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    //题目答案
    private int questionAnswer;
    //题目类型0:判断1:单选2:多选
    private int questionType;
    //题目图片src
    private String questionImg;
    //题目说明
    private String questionExplain;

    public String getQuestionExplain() {
        return questionExplain;
    }

    public Question setQuestionExplain(String questionExplain) {
        this.questionExplain = questionExplain;
        return this;
    }

    public String getQuestionID() {
        return questionID;
    }

    public Question setQuestionID(String questionID) {
        this.questionID = questionID;
        return this;
    }

    public String getQuestionProblem() {
        return questionProblem;
    }

    public Question setQuestionProblem(String questionProblem) {
        this.questionProblem = questionProblem;
        return this;
    }

    public int getQuestionAnswer() {
        return questionAnswer;
    }

    public Question setQuestionAnswer(int questionAnswer) {
        this.questionAnswer = questionAnswer;
        return this;
    }

    public int getQuestionType() {
        return questionType;
    }

    public Question setQuestionType(int questionType) {
        this.questionType = questionType;
        return this;
    }

    public String getQuestionImg() {
        return questionImg;
    }

    public void setQuestionImg(String questionImg) {
        this.questionImg = questionImg;
    }

    public String getOption1() {
        return option1;
    }

    public Question setOption1(String option1) {
        this.option1 = option1;
        return this;
    }

    public String getOption2() {
        return option2;
    }

    public Question setOption2(String option2) {
        this.option2 = option2;
        return this;
    }

    public String getOption3() {
        return option3;
    }

    public Question setOption3(String option3) {
        this.option3 = option3;
        return this;
    }

    public String getOption4() {
        return option4;
    }

    public Question setOption4(String option4) {
        this.option4 = option4;
        return this;
    }
}
