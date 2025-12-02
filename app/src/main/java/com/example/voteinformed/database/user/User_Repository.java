package com.example.voteinformed.database.user;

import android.content.Context;

import androidx.room.Room;

public class User_Repository {
    private String DB_NAME = "userdb";

    private User_Database user_database;

    Context context;

    public User_Repository(Context context){
        this.context = context;
        user_database = Room.databaseBuilder(context, User_Database.class, DB_NAME).build();
    }
}
