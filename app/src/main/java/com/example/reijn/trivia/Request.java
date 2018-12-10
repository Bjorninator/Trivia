package com.example.reijn.trivia;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Request implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private ArrayList<question> list;
    private Callback iets;

    @Override
    public void onErrorResponse(VolleyError error) {
        iets.gotQuestionError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {

            JSONArray question = response.getJSONArray("results");
            list  = new ArrayList<question>();
            for (int i = 0; i < question.length(); i++) {
                JSONObject object = question.getJSONObject(i);
                question item = new question(object.getString("category"),object.getString("type"),
                        object.getString("difficulty"), object.getString("question"),
                        object.getString("correct_answer"), object.getString("incorrect_answers"));
                list.add(item);
            }
        }

        catch (JSONException e){
            System.out.println(e.getMessage());
        }
        iets.gotQuestion(list);

    }

    public interface Callback {
        void gotQuestion(ArrayList<question> question);
        void gotQuestionError(String message);
    }

    public Request(Context context){
        this.context = context;
    }
    void getQuestion(Callback activity){
        System.out.println("hallooooo");
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://opentdb.com/api.php?amount=10&type=multiple", null, this, this);
        queue.add(jsonObjectRequest);
        iets = activity;
    }
}
