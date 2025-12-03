package com.example.voteinformed.data.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import androidx.room.Room;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.voteinformed.data.dao.Article_Dao;
import com.example.voteinformed.data.dao.Election_Dao;
import com.example.voteinformed.data.dao.Issue_Dao;
import com.example.voteinformed.data.dao.Politician_Dao;
import com.example.voteinformed.data.dao.User_Dao;
import com.example.voteinformed.data.database.VoteInformed_Database;

public class VoteInformed_Repository {

    private final String DB_NAME = "VoteInformed DB";
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final VoteInformed_Database voteInformedDatabase; // NOTE: Shouldn't need this in the constructor because we are using context, maybe
    private final Article_Dao articleDao;
    private final Election_Dao electionDao;
    private final Issue_Dao issueDao;
    private final Politician_Dao politicianDao;
    private final User_Dao userDao;

    //Context context;

    public VoteInformed_Repository(Context context)
    {
        //this.context = context;
        voteInformedDatabase = Room.databaseBuilder(context, VoteInformed_Database.class, DB_NAME).build();
        articleDao = voteInformedDatabase.articleDao();
        electionDao = voteInformedDatabase.electionDao();
        issueDao = voteInformedDatabase.issueDao();
        politicianDao = voteInformedDatabase.politicianDao();
        userDao = voteInformedDatabase.userDao();


        Toast.makeText(context, "Database initialized...", Toast.LENGTH_LONG).show();
    }

    //--------------- Insert Task --------------------

}
