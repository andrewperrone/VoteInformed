package com.example.voteinformed.data.util;

import android.content.Context;

import com.example.voteinformed.data.database.VoteInformed_Database;
import com.example.voteinformed.data.dao.Article_Dao;
import com.example.voteinformed.data.dao.Election_Dao;
import com.example.voteinformed.data.dao.Issue_Dao;
import com.example.voteinformed.data.dao.Politician_Dao;
import com.example.voteinformed.data.dao.User_Dao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


// does the first population, only when the db has not been made before
public class DatabaseClient {
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void seedDatabase(Context context, VoteInformed_Database db) {

        executor.execute(() -> {

            Article_Dao articleDao = db.articleDao();
            Election_Dao electionDao = db.electionDao();
            Issue_Dao issueDao = db.issueDao();
            Politician_Dao politicianDao = db.politicianDao();
            User_Dao userDao = db.userDao();

            //add lists
            articleDao.insertAll(InitialData.getArticles());
            electionDao.insertAll(InitialData.getElections());
            issueDao.insertAll(InitialData.getIssues());
            politicianDao.insertAll(InitialData.getPoliticians());
            userDao.insertAll(InitialData.getUsers());
        });
    }
}
