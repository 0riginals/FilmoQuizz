package com.test.filmoquizz.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Paul VINOT, Antoine COLPAERT, Yuting JIN
 */
public class QuestionBank {
    private ArrayList<Question> questionList;
    private int nextQuestionIndex;


    public QuestionBank(ArrayList<Question> q) {
        this.questionList = q;

        Collections.shuffle(questionList);
        nextQuestionIndex = 0;
    }

    public Question getQuestion() {
        if (nextQuestionIndex == questionList.size())
            nextQuestionIndex = 0;
        return questionList.get(nextQuestionIndex++);
    }

    public void addQuestion(Question question) {
        this.questionList.add(question);
    }

}
