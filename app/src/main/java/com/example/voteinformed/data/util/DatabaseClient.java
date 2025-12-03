package com.example.voteinformed.data.util;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.voteinformed.data.dao.Article_Dao;
import com.example.voteinformed.data.dao.Election_Dao;
import com.example.voteinformed.data.dao.Issue_Dao;
import com.example.voteinformed.data.dao.Politician_Dao;
import com.example.voteinformed.data.dao.User_Dao;
import com.example.voteinformed.data.database.VoteInformed_Database;

import java.util.concurrent.Executors;

public class DatabaseClient {

    private static VoteInformed_Database instance;

    public static VoteInformed_Database getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            VoteInformed_Database.class, "voteinformed_db")
                    .addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);

                            Executors.newSingleThreadExecutor().execute(() -> {
                                Article_Dao articleDao = instance.articleDao();
                                Election_Dao electionDao = instance.electionDao();
                                Issue_Dao issueDao = instance.issueDao();
                                Politician_Dao politicianDao = instance.politicianDao();
                                User_Dao userDao = instance.userDao();

                                articleDao.insertAll(InitialData.getArticles());
                                electionDao.insertAll(InitialData.getElections());
                                issueDao.insertAll(InitialData.getIssues());
                                politicianDao.insertAll(InitialData.getPoliticians());
                                userDao.insertAll(InitialData.getUsers());
                            });
                        }
                    })
                    .build();
        }
        return instance;
    }
}

