package com.example.retita.topquiz.Model;

import java.util.Collections;
import java.util.List;

public class QuestionBank {

    private List<Question> questionList;
    private int nextQuestionIndex;

    public QuestionBank(List<Question> questionList){
        this.questionList = questionList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
        Collections.shuffle(this.questionList);
    }

    public int getNextQuestionIndex() {
        return nextQuestionIndex;
    }

    public void setNextQuestionIndex(int nextQuestionIndex) {
        this.nextQuestionIndex = nextQuestionIndex;
    }

    public Question getQuestion() {
        // Ensure we loop over the questions
        if (this.nextQuestionIndex == this.questionList.size()) {
            this.nextQuestionIndex = 0;
        }

        // Please note the post-incrementation
        return this.questionList.get(this.nextQuestionIndex++);
    }
}
