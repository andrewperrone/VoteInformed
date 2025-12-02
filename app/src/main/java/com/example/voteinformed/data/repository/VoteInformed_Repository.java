package com.example.voteinformed.data.repository;

import android.content.Context;
import androidx.room.Room;

import com.example.voteinformed.data.database.VoteInformed_Database;

public class VoteInformed_Repository {

    private String DB_NAME = "VoteInformed DB";
    private VoteInformed_Database voteInformedDatabase; // NOTE: Shouldn't need this in the constructor because we are using context
    Context context;

    public VoteInformed_Repository(Context context)
    {
        this.context = context;
        voteInformedDatabase = Room.databaseBuilder(context, VoteInformed_Database.class, DB_NAME).build();
    }
}
