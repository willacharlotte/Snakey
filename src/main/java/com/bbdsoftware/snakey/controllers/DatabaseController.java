package com.bbdsoftware.snakey.controllers;

import com.bbdsoftware.snakey.domain.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedHashMap;

public class DatabaseController {

    String url = "jdbc:sqlserver://localhost:1433;databaseName=StatisticsDB;portNumber=1433;trustServerCertificate=true;integratedSecurity=true";
    Statement statement;
    ResultSet results;

    public DatabaseController(){
    }

    /**
     * Starts connection
     */
    private Connection DBConnect() {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void DBClose(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Adds score to database
     * @param user
     */
    public void AddPlayerRecord(User user) {
        Connection conn = DBConnect();
        String name=user.getUsername();
        Integer score=user.getScore();
        System.out.println(name);
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO Leaderboard ([userName],score,gameDate) VALUES (?,?,?)");
            statement.setString(1, name);
            statement.setInt(2, score);
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBClose(conn);
    }

    /**
     * Gets the top 10 scores
     * @return
     */
    public LinkedHashMap<String, Integer> GetPlayerRecord() {
        Connection conn = DBConnect();
        LinkedHashMap<String, Integer> scores = new LinkedHashMap<>();
        try {
            statement = conn.createStatement();
            results = statement.executeQuery("Select *, ROW_NUMBER() OVER (ORDER BY score DESC) RowNumber FROM Leaderboard");
            while (results.next()) {
               scores.put(results.getString(2), results.getInt("score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBClose(conn);
        return scores;
    }

    /**
     * Gets the row of user that is not part of the top 10
     * @param user
     * @return
     */
    public int GetUserRow(User user){
        Connection conn = DBConnect();
        String name = user.getUsername();
        int rowCount = -1;
        try {
            statement = conn.createStatement();
            results = statement.executeQuery("Select *,ROW_NUMBER() OVER (ORDER BY score DESC) RowNumber from Leaderboard");
            while (results.next()) {
                if (results.getString(2).equalsIgnoreCase(name)){
                    rowCount = results.getInt("RowNumber");
                }
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBClose(conn);
        return rowCount;

    }
}
