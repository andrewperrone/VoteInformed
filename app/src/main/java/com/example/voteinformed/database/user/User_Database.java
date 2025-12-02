package com.example.voteinformed.database.user;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class User_Database extends RoomDatabase {
    public abstract User_Dao User_Dao();

}
