package com.example.reijn.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class QuestionActivity extends AppCompatActivity implements QuestionRequest.Callback {
    int counter = 0;
    int points = 0;
    Button A;
    Button B;
    Button C;
    Button D;
    TextView text;
    TextView punten;
    ArrayList<question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        QuestionRequest x = new QuestionRequest(this);
        x.getQuestion(this);

        A = findViewById(R.id.A);
        B = findViewById(R.id.B);
        C = findViewById(R.id.C);
        D = findViewById(R.id.D);
        text = findViewById(R.id.textView2);
        punten = findViewById(R.id.textView6);
    }

    @Override
    public void gotQuestion(ArrayList<question> question) {
        questions = question;
        fill();
    }

    @Override
    public void gotQuestionError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void fill() {
        Button[] right = {A, B, C, D};

        Random rand = new Random();
        Button randomBtn = right[rand.nextInt(right.length)];
        text.setText(questions.get(counter).getQuestion());
        randomBtn.setText(questions.get(counter).getCorrect());
        if (randomBtn == A) {
            Button[] wrong = {B, C, D};
            wrong[0].setText(questions.get(counter).getIncorrect1());
            wrong[1].setText(questions.get(counter).getIncorrect2());
            wrong[2].setText(questions.get(counter).getIncorrect3());

        }else if(randomBtn == B) {
            Button[] wrong = {A, C, D};
            wrong[0].setText(questions.get(counter).getIncorrect1());
            wrong[1].setText(questions.get(counter).getIncorrect2());
            wrong[2].setText(questions.get(counter).getIncorrect3());

        }else if(randomBtn == C) {
            Button[] wrong = {B, A, D};
            wrong[0].setText(questions.get(counter).getIncorrect1());
            wrong[1].setText(questions.get(counter).getIncorrect2());
            wrong[2].setText(questions.get(counter).getIncorrect3());

        }else if(randomBtn == D) {
            Button[] wrong = {B, C, A};
            wrong[0].setText(questions.get(counter).getIncorrect1());
            wrong[1].setText(questions.get(counter).getIncorrect2());
            wrong[2].setText(questions.get(counter).getIncorrect3());
        }


    }

    public void clicked(View view) {
        Button button = (Button) view;
        if(button.getText() == questions.get(counter).getCorrect()){
            points++;
        }
        counter++;
        punten.setText("punten: " + points);
        if(counter == 10 ){
            Intent intent = new Intent(QuestionActivity.this, uploadActivity.class);
            intent.putExtra("punten", points);
            startActivity(intent);
        }else{
            fill();
        }
    }
}