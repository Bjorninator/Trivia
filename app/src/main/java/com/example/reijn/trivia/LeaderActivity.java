package com.example.reijn.trivia;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class LeaderActivity extends AppCompatActivity implements LeaderRequest.Callback {
    LeaderAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SystemClock.sleep(1000);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader);
        LeaderRequest x = new LeaderRequest(this);
        x.getQuestion(this);
    }

    @Override
    public void gotQuestion(ArrayList<leaderboard> question) {
        adapter = new LeaderAdapter(this, question);
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
    }

    @Override
    public void gotQuestionError(String message) {

    }
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
