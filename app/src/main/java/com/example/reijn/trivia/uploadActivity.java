package com.example.reijn.trivia;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import org.json.JSONObject;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class uploadActivity extends AppCompatActivity {
    TextInputLayout naam;
    TextView view;
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
        String text = naam.getEditText().getText().toString();
         https://ide50-reijndersbjorn.cs50.io:8080/list -d "naam=fred" -d "punten=3" -X POST
         try {
             URL url = new URL("https://ide50-reijndersbjorn.cs50.io:8080");
             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
             conn.setRequestMethod("POST");
             conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
             conn.setRequestProperty("Accept","application/json");
             conn.setDoOutput(true);
             conn.setDoInput(true);

             JSONObject jsonParam = new JSONObject();
             jsonParam.put("naam", text);
             jsonParam.put("punten", points);

             Log.i("JSON", jsonParam.toString());
             DataOutputStream os = new DataOutputStream(conn.getOutputStream());
             //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
             os.writeBytes(jsonParam.toString());

             os.flush();
             os.close();

             Log.i("STATUS", String.valueOf(conn.getResponseCode()));
             Log.i("MSG" , conn.getResponseMessage());

             conn.disconnect();
         } catch (Exception e) {
             e.printStackTrace();
         }

    }
}
