package com.example.voteinformed.data.entity.relation;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"politician_id", "issue_id"})//(tableName = "politician_issue")
public class Politician_Issue {

    private int politician_id;
    private int issue_id;

    private String opinion;

    public Politician_Issue(int politician_id, int issue_id, String opinion) {
        this.politician_id = politician_id;
        this.issue_id = issue_id;
    }

    public int getPolitician_id() {
        return politician_id;
    }

    public void setPolitician_id(int politician_id) {
        this.politician_id = politician_id;
    }

    public int getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(int issue_id) {
        this.issue_id = issue_id;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}

