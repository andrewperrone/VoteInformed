package com.example.voteinformed.database.user;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_issue")
public class User_Issue {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="user_issue_id")
    private int user_issue_id;

    public User_Issue() {
    }

    public int getUser_issue_id() {
        return user_issue_id;
    }

    public void setUser_issue_id(int user_issue_id) {
        this.user_issue_id = user_issue_id;
    }
}
