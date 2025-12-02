package com.example.voteinformed.database.user;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "user_politician")
public class User_Politician {
    @ColumnInfo(name = "user_id")
    private int user_id;

    @ColumnInfo(name = "politician_id")
    private int politician_id;

    public User_Politician() {
    }
}
