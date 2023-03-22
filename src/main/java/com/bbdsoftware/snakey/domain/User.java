package com.bbdsoftware.snakey.domain;

public class User {
    private final String username;
    private final float score;

    public User(String username, float score){
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public float getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", score=" + score +
                '}';
    }
}
