package com.bbdsoftware.snakey.domain;

public class User {
    private final String username;
    private final Integer score;

    public User(String username, Integer score){
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public Integer getScore() {
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
