package com.example.reijn.trivia;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class uploadActivity extends AppCompatActivity {
    TextInputLayout naam;
    TextView view;
    String text;
    int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        Intent intent = getIntent();
        points = (int) intent.getSerializableExtra("punten");
        view = (TextView) findViewById(R.id.textView3);
        naam = (TextInputLayout) findViewById(R.id.layout);
        view.setText("Wow jij bent echt heel goed maar liefst " + points + " punten");
    }

    public void click(View view) {
        text = naam.getEditText().getText().toString();
        if (text == null) {
            text = "anoniem";
        }
        //   https://ide50-reijndersbjorn.cs50.io:8080/list -d "naam=fred" -d "punten=3" -X POST
        RequestQueue myQueue = Volley.newRequestQueue(this);
        String url = "https://ide50-reijndersbjorn.cs50.io:8080/list";
        StringRequest strings = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("name", text); //Add the data you'd like to send to the server.
                MyData.put("points", "" + points + "");
                return MyData;
            }
        };
        myQueue.add(strings);
        Intent intent = new Intent(uploadActivity.this, LeaderActivity.class);
        startActivity(intent);
    }


}
