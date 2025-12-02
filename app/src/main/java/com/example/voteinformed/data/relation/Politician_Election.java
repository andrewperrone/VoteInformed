package com.example.voteinformed.data.relation;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "politician_election")
public class Politician_Election {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="politician_election_id")
    private int politician_election_id;

    public Politician_Election() {
    }

    public int getPolitician_election_id() {
        return politician_election_id;
    }

    public void setPolitician_election_id(int politician_election_id) {
        this.politician_election_id = politician_election_id;
    }

    @NonNull
    @Override
    public String toString() {
        return "Politician_Election{" +
                "politician_election_id=" + politician_election_id +
                '}';
    }
}
