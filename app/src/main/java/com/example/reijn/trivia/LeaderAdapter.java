package com.example.reijn.trivia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LeaderAdapter extends ArrayAdapter<leaderboard> {
    private ArrayList<leaderboard> leaderboards;
    ImageView image;

    public LeaderAdapter(@NonNull Context context, @NonNull ArrayList<leaderboard> objects) {
        super(context, R.layout.activity_leader, objects);
        this.leaderboards = objects;
        sortArrayList();
    }

    private void sortArrayList(){
        Collections.sort(leaderboards, new Comparator<leaderboard>() {
            @Override
            public int compare(leaderboard t1, leaderboard t2) {
                return t2.getPoints().compareTo(t1.getPoints());
            }
        });
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.leader_row, parent, false);
        }

        TextView name = convertView.findViewById(R.id.textView5);
        TextView points = convertView.findViewById(R.id.textView4);

        leaderboard leaderboard = leaderboards.get(position);
        String nametext = leaderboard.getName();
        String pointstext = leaderboard.getPoints();

        name.setText(nametext);
        points.setText("punten: " + pointstext);


        return convertView;

    }
}