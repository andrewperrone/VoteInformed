package com.example.voteinformed.database.user;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_politician")
public class User_Politician {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="user_politician_id")
    private int user_politician_id;

    public User_Politician() {
    }

    public int getUser_politician_id() {
        return user_politician_id;
    }

    public void setUser_politician_id(int user_politician_id) {
        this.user_politician_id = user_politician_id;
    }
}
