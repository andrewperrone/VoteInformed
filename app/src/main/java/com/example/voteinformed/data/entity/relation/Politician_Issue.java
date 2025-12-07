package com.example.voteinformed.data.entity.relation;
import androidx.room.Entity;
import androidx.room.Index;

@Entity(primaryKeys = {"politician_id", "issue_id"},
        indices = {@Index(value = {"issue_id"})})
public class Politician_Issue {

    public int politician_id;
    public int issue_id;
    public String opinion;

    public Politician_Issue(int politician_id, int issue_id, String opinion) {
        this.politician_id = politician_id;
        this.issue_id = issue_id;
        this.opinion = opinion;
    }
}

