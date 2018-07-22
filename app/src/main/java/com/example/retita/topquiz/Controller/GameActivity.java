package com.example.retita.topquiz.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retita.topquiz.Model.Question;
import com.example.retita.topquiz.Model.QuestionBank;
import com.example.retita.topquiz.R;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private Boolean enableTouchEvent;
    private QuestionBank questionBank;
    private Button rep1;
    private Button rep2;
    private Button rep3;
    private Button rep4;
    private TextView question;
    private int numberOfQuestions;
    private int score;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        rep1 = (Button)findViewById(R.id.answer1_id);
        rep2 = (Button)findViewById(R.id.answer2_id);
        rep3 = (Button)findViewById(R.id.answer3_id);
        rep4 = (Button)findViewById(R.id.answer4_id);
        question = (TextView)findViewById(R.id.question_id);
        enableTouchEvent =true;

        if (savedInstanceState != null) {

            score = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            numberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);

        } else {

            score = 0;
            numberOfQuestions = 4;

        }


        questionBank = this.generateQuestions();
        displayQuestion(questionBank.getQuestion());

        rep1.setOnClickListener(this);
        rep2.setOnClickListener(this);
        rep3.setOnClickListener(this);
        rep4.setOnClickListener(this);

        rep1.setTag(0);
        rep2.setTag(1);
        rep3.setTag(2);
        rep4.setTag(3);

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


    private void displayQuestion(final Question question) {

        this.question.setText(question.getQuestion());
        this.rep1.setText(question.getChoiceList().get(0));
        this.rep2.setText(question.getChoiceList().get(1));
        this.rep3.setText(question.getChoiceList().get(2));
        this.rep4.setText(question.getChoiceList().get(3));

    }

    @Override

    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt(BUNDLE_STATE_SCORE, score);
        outState.putInt(BUNDLE_STATE_QUESTION, numberOfQuestions);

        super.onSaveInstanceState(outState);
    }


    @Override
    public void onClick(View view) {
        int responseIndex = (int) view.getTag();

        if( responseIndex == questionBank.getQuestion().getAnswerIndex()){
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            score += 1;

        }else {
            Toast.makeText(this, "No Correct!", Toast.LENGTH_SHORT).show();

        }

        enableTouchEvent = false;

        new Handler().postDelayed(new Runnable() {

            @Override

            public void run() {
                enableTouchEvent = true;

                if (-- numberOfQuestions == 0) {

                    endGame();


                } else {


                    displayQuestion(questionBank.getQuestion());

                }

            }

        }, 2000); // LENGTH_SHORT is usually 2 second long



    }

    public void endGame(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Well done!")
                .setMessage("Your score is " + score)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE,score);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .create()
                .show();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev) && enableTouchEvent;
    }



    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("GameActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("GameActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("GameActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("GameActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("GameActivity::onDestroy()");
    }
}
