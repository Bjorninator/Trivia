package com.example.reijn.trivia;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderRequest implements Response.Listener<JSONArray>, Response.ErrorListener {

    private Context context;
    private ArrayList<leaderboard> list;
    private Callback iets;

    @Override
    public void onErrorResponse(VolleyError error) {
        iets.gotQuestionError(error.getMessage());
    }

    @Override
    public void onResponse(JSONArray response) {
        try {

            JSONArray question = response;
            list  = new ArrayList<leaderboard>();
            for (int i = 0; i < question.length(); i++) {
                JSONObject object = question.getJSONObject(i);
                leaderboard item = new leaderboard(object.getString("name"),object.getString("points"));
               list.add(item);
            }
        }

        catch (JSONException e){
            System.out.println(e.getMessage());
        }
        iets.gotQuestion(list);

    }

    public interface Callback {
        void gotQuestion(ArrayList<leaderboard> question);
        void gotQuestionError(String message);
    }

    public LeaderRequest(Context context){
        this.context = context;
    }
    void getQuestion(Callback activity){
        System.out.println("hallooooo");
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest("https://ide50-reijndersbjorn.cs50.io:8080/list", this, this);
        queue.add(jsonObjectRequest);
        iets = activity;
    }
}
