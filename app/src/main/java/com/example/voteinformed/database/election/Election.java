package com.example.voteinformed.database.election;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "election")
public class Election {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="election_id")
    private int election_id;

    public Election() {
    }

    public int getElection_id() {
        return election_id;
    }

    public void setElection_id(int election_id) {
        this.election_id = election_id;
    }

    @Override
    public String toString() {
        return "Election{" +
                "election_issue_id=" + election_id +
                '}';
    }


}
