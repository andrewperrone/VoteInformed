package com.example.voteinformed.database;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users", indices = {@Index(value = {"username"}, unique = true), @Index(value = {"userEmail"}, unique = true)})
public class Users {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    private String password;
    private String userEmail;

    public Users(String username, String password, String userEmail) {
        this.username = username;
        this.password = password;
        this.userEmail = userEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
