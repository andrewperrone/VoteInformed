package com.example.voteinformed.database.issue;

import android.content.Context;

import androidx.room.Room;

import com.example.voteinformed.database.user.User_Database;

public class Issue_Repository {
    private String DB_NAME = "issuedb";

    private Issue_Database issue_database;

    Context context;

    public Issue_Repository(Context context){
        this.context = context;
        issue_database = Room.databaseBuilder(context, Issue_Database.class, DB_NAME).build();
    }
}
