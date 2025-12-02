package com.example.voteinformed.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.voteinformed.database.article.Article;
import com.example.voteinformed.database.article.Article_Election;
import com.example.voteinformed.database.article.Article_Issue;
import com.example.voteinformed.database.article.Article_Politician;
import com.example.voteinformed.database.election.Election;
import com.example.voteinformed.database.issue.Issue;
import com.example.voteinformed.database.politician.Politician;
import com.example.voteinformed.database.politician.Politician_Dao;
import com.example.voteinformed.database.politician.Politician_Election;
import com.example.voteinformed.database.politician.Politician_Issue;
import com.example.voteinformed.database.user.User;
import com.example.voteinformed.database.user.User_Issue;
import com.example.voteinformed.database.user.User_Politician;
import com.example.voteinformed.database.user.User_Article;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {  Article.class, Article_Election.class, Article_Issue.class, Article_Politician.class,
                        Election.class,
                        Issue.class,
                        Politician.class, Politician_Election.class, Politician_Issue.class,
                        User.class, User_Article.class, User_Issue.class, User_Politician.class},
                        version = 1,
                        exportSchema = false)
public abstract class VoteInformed_Database extends RoomDatabase {

    /*// Connects the database to the DAO
    public abstract Politician_Dao politician_dao();

    // Singleton pattern
    private static volatile VoteInformed_Database INSTANCE;

    // thread pool for running database operations
    // asynchronously on a background thread.
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static VoteInformed_Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (VoteInformed_Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    VoteInformed_Database.class, "vote_informed_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }*/
}

