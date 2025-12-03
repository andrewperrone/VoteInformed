package com.example.voteinformed.data.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
import com.example.voteinformed.data.util.DatabaseClient;

@Database(
        entities = {
                // Entities
                User.class,
                Article.class,
                Issue.class,
                Politician.class,
                Election.class,

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
        exportSchema = true
)
@TypeConverters({Converters.class})
public abstract class VoteInformed_Database extends RoomDatabase {

    // DOA GETTERS
    public abstract User_Dao userDao();

    public abstract Article_Dao articleDao();

    public abstract Issue_Dao issueDao();

    public abstract Election_Dao electionDao();

    public abstract Politician_Dao politicianDao();

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
                            .fallbackToDestructiveMigration()

                            // POPULATE!!!
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase dbObj) {
                                    super.onCreate(dbObj);
                                    DatabaseClient.seedDatabase(appContext, INSTANCE);
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
/*    // IMPORTANT MUST POPULATE HERE OR CAN GET DUPLICATEs
    private static final RoomDatabase.Callback databaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase dbObj) {
                    super.onCreate(dbObj);

                    // Seed using the SAME instance returned by getInstance()
                    DatabaseClient.seedDatabase(appContext, INSTANCE);
                }
            };

}*/
