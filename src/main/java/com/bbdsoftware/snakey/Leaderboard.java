package com.bbdsoftware.snakey;

import java.sql.*;
import java.util.*;

public class Leaderboard {

    // String url = "jdbc:sqlserver://PalesaM\\SQLEXPRESS;databaseName=StatisticsDB;portNumber=1433;trustServerCertificate=true;integratedSecurity=true";
    String url = "jdbc:sqlserver://localhost:1433;databaseName=StatisticsDB;portNumber=1433;trustServerCertificate=true;integratedSecurity=true";
    //String user="ahgdmin";
    //String password = "passwor1d";
    Connection conn;
    Statement statement;
    ResultSet results;

    public Leaderboard() {
        DBConnect();
    }
    
    private void DBConnect() {
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addScore(String user, Integer score) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO Leaderboard ([user],score) VALUES (?,?)");
            statement.setString(1, user);
            statement.setInt(2, score);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Integer> GetScores() {
        HashMap<String, Integer> scores = new HashMap<>();
        try {
            statement = conn.createStatement();
            results = statement.executeQuery("Select * from Leaderboard");
            while (results.next()) {
                System.out.println(results.getString(2) + results.getString(3));
                scores.put(results.getString(2), results.getInt("score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scores;
    }


}
