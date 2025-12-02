package com.example.voteinformed.database.user;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "user_issue")
public class User_Issue {
    @ColumnInfo(name = "user_id")
    private int user_id;

    @ColumnInfo(name = "issue_id")
    private int issue_id;

    public User_Issue(int user_id, int issue_id){
        this.user_id = user_id;
        this.issue_id = issue_id;
    }

    public int getUserId(){
        return user_id;
    }

    public void setUserId(){
        this.user_id = user_id;
    }

    public int getIssue_id(){
        return issue_id;
    }

    public void setIssueId(){
        this.issue_id = issue_id;
    }

}
