package com.example.voteinformed.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Politician.class, Users.class}, version = 2)
public abstract class VoteInformedDatabase extends RoomDatabase {

    // Connects the database to the DAO
    public abstract PoliticianDao politicianDao();
    public abstract UsersDAO usersDao();

    // Singleton pattern
    private static volatile VoteInformedDatabase INSTANCE;

    // thread pool for running database operations
    // asynchronously on a background thread.
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static VoteInformedDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (VoteInformedDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    VoteInformedDatabase.class, "vote_informed_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
