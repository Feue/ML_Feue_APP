package com.feue.ml.adapter.entity;

public class PracticeInfo {
    private String name;
    private int questionNum;

    public PracticeInfo() {
    }

    public PracticeInfo(String name, int questionNum) {
        this.name = name;
        this.questionNum = questionNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    @Override
    public String toString() {
        return "PracticeInfo{" +
                "name='" + name + '\'' +
                ", questionNum=" + questionNum +
                '}';
    }
}
