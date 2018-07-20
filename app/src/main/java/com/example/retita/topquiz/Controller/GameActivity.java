package com.example.retita.topquiz.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.retita.topquiz.Model.Question;
import com.example.retita.topquiz.Model.QuestionBank;
import com.example.retita.topquiz.R;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity {

    QuestionBank questionBank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        questionBank = this.generateQuestions();
    }

    private QuestionBank generateQuestions(){

        Question question1 = new Question("Who is the creator of Android?",
                Arrays.asList("Andy Rubin",
                        "Steve Wozniak",
                        "Jake Wharton",
                        "Paul Smith"),
                0);


        Question question2 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958",
                        "1962",
                        "1967",
                        "1969"),
                3);


        Question question3 = new Question("What is the house number of The Simpsons?",

                Arrays.asList("42",

                        "101",

                        "666",

                        "742"),

                3);


        return new QuestionBank(Arrays.asList(question1,

                question2,

                question3));
    }
}
