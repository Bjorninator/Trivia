package com.example.reijn.trivia;

public class leaderboard {
    private String name, points;

    public leaderboard(String name, String points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public String getPoints() {
        return points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
