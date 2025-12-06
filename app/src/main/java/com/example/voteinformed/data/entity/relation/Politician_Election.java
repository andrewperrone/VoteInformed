package com.example.voteinformed.data.entity.relation;
import androidx.room.Entity;

import java.util.Objects;

@Entity(primaryKeys = {"election_id", "politician_id"})//(tableName = "politician_election")
public class Politician_Election {
    private int politician_id;
    private int election_id;
    private String position;

    public Politician_Election(int politician_id, int election_id, String position) {
        this.politician_id = politician_id;
        this.election_id = election_id;
        this.position = position;
    }

    public int getPolitician_id() {
        return politician_id;
    }

    public void setPolitician_id(int politician_id) {
        this.politician_id = politician_id;
    }

    public int getElection_id() {
        return election_id;
    }

    public void setElection_id(int election_id) {
        this.election_id = election_id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Politician_Election{" +
                "politician_id=" + politician_id +
                ", election_id=" + election_id +
                ", position='" + position + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Politician_Election that = (Politician_Election) o;
        return politician_id == that.politician_id && election_id == that.election_id && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(politician_id, election_id, position);
    }
}
