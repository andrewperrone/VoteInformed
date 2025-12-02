package com.example.voteinformed.database.issue;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Issue {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="issue_id")
    private int issue_id;

    public Issue() {
    }

    public int getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(int issue_id) {
        this.issue_id = issue_id;
    }
}
