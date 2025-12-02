package com.example.voteinformed.database.issue;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Issue.class}, version = 1, exportSchema = false)
public abstract class Issue_Database extends RoomDatabase {
    public abstract Issue_Dao Issue_Dao();
}
