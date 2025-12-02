package com.example.voteinformed.database.user;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_election")
public class User_Election {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="user_election_id")
    private int user_election_id;

    public User_Election() {
    }

    public int getUser_election_id() {
        return user_election_id;
    }

    public void setUser_election_id(int user_election_id) {
        this.user_election_id = user_election_id;
    }
}
