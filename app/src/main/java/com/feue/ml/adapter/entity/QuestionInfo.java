package com.feue.ml.adapter.entity;

import java.util.Arrays;

public class QuestionInfo {
    private String description;
    private String[] options;
    private boolean[] answer = new boolean[4];
    private boolean[] userAnswer = new boolean[4];

    public QuestionInfo() {
    }

    public QuestionInfo(String description, String[] options, int answer) {
        this.description = description;
        this.options = options;
        this.answer[answer] = true;
    }

    public QuestionInfo(String description, String[] options, int[] answer) {
        this.description = description;
        this.options = options;
        for (int i : answer) {
            this.answer[i] = true;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public boolean[] getAnswer() {
        return answer;
    }

    public void setAnswer(boolean[] answer) {
        this.answer = answer;
    }

    public boolean[] getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(boolean[] userAnswer) {
        this.userAnswer = userAnswer;
    }

    @Override
    public String toString() {
        return "QuestionInfo{" +
                "description='" + description + '\'' +
                ", options=" + Arrays.toString(options) +
                ", answer=" + answer +
                ", userAnswer=" + userAnswer +
                '}';
    }
}
