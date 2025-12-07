package com.example.voteinformed.data.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.voteinformed.data.dao.SavedArticle_Dao;
import com.example.voteinformed.data.entity.SavedArticle;
import com.example.voteinformed.data.util.Converters;

//dao
import com.example.voteinformed.data.dao.Article_Dao;
import com.example.voteinformed.data.dao.Election_Dao;
import com.example.voteinformed.data.dao.Issue_Dao;
import com.example.voteinformed.data.dao.Politician_Dao;
import com.example.voteinformed.data.dao.User_Dao;

//entities
import com.example.voteinformed.data.entity.User;
import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.Issue;
import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.entity.Election;

// Relations
import com.example.voteinformed.data.entity.relation.User_Article;
import com.example.voteinformed.data.entity.relation.User_Issue;
import com.example.voteinformed.data.entity.relation.User_Election;
import com.example.voteinformed.data.entity.relation.User_Politician;
import com.example.voteinformed.data.entity.relation.Article_Issue;
import com.example.voteinformed.data.entity.relation.Article_Election;
import com.example.voteinformed.data.entity.relation.Article_Politician;
import com.example.voteinformed.data.entity.relation.Politician_Election;
import com.example.voteinformed.data.entity.relation.Politician_Issue;

import java.util.concurrent.Executors;
import com.example.voteinformed.data.util.InitialData;

@Database(
        entities = {
                // Entities
                User.class,
                Article.class,
                Issue.class,
                Politician.class,
                Election.class,
                SavedArticle.class,

                // Relations
                User_Article.class,
                User_Issue.class,
                User_Election.class,
                User_Politician.class,
                Article_Issue.class,
                Article_Election.class,
                Article_Politician.class,
                Politician_Election.class,
                Politician_Issue.class
        },
        version = 1,
        exportSchema = false
)
@TypeConverters({Converters.class})
public abstract class VoteInformed_Database extends RoomDatabase {

    // DOA GETTERS
    public abstract User_Dao userDao();

    public abstract Article_Dao articleDao();

    public abstract Issue_Dao issueDao();

    public abstract Election_Dao electionDao();

    public abstract Politician_Dao politicianDao();

    public abstract SavedArticle_Dao savedArticleDao();


    //IMPORTANT NEEDS SINGLE INSTANCE SO MULTIPLE DON'T GET MADE
    private static volatile VoteInformed_Database INSTANCE;

    public static VoteInformed_Database getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (VoteInformed_Database.class) {
                if (INSTANCE == null) {
                    final Context appContext = context.getApplicationContext();


                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    VoteInformed_Database.class,
                                    "vote_informed_database"
                            )
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // In VoteInformed_Database.java (around line 110-120)

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // This task runs ONLY when the database is first created
            Executors.newSingleThreadExecutor().execute(() -> {
                Politician_Dao dao = INSTANCE.politicianDao();

                // Iterate through the list and insert each politician individually
                for (Politician p : InitialData.getPoliticians()) {
                    dao.insert(p);
                }
            });
        }
    };
}
