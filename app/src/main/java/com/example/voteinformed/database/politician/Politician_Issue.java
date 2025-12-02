package com.example.voteinformed.database.politician;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "politician_issue")
public class Politician_Issue {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="politician_issue_id")
    private int politician_issue_id;

    public Politician_Issue() {
    }

    public int getPolitician_issue_id() {
        return politician_issue_id;
    }

    public void setPolitician_issue_id(int politician_issue_id) {
        this.politician_issue_id = politician_issue_id;
    }

    @Override
    public String toString() {
        return "Politician_Issue{" +
                "politician_issue_id=" + politician_issue_id +
                '}';
    }
}
