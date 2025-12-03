package com.example.voteinformed.data.util;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.voteinformed.data.dao.Election_Dao;
import com.example.voteinformed.data.dao.Politician_Dao;
import com.example.voteinformed.data.database.VoteInformed_Database;
import com.example.voteinformed.data.entity.Election;
import com.example.voteinformed.data.entity.Politician;

import java.util.concurrent.Executors;
import java.util.Date;

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
                                // Insert dummy data here
                                Politician_Dao politicianDao = instance.politicianDao();
                                Election_Dao electionDao = instance.electionDao();

                                // Example: add some dummy politicians
                                byte[] image = new byte[1];
                                politicianDao.insert(new Politician("John Doe", "Party A", image));
                                politicianDao.insert(new Politician("Jane Smith", "Party B", image));

                                // Example: add some dummy elections
                                electionDao.insert(new Election(
                                        "Presidential Election",
                                        new Date(),
                                        "State X",
                                        "Description of election"
                                ));
                                electionDao.insert(new Election(
                                        "Senate Election",
                                        new Date(),
                                        "State Y",
                                        "Another description"
                                ));
                            });
                        }
                    })
                    .build();
        }
        return instance;
    }
}

