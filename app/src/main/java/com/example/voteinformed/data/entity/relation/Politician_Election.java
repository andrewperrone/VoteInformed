package com.example.voteinformed.data.entity.relation;
import androidx.room.Entity;
import androidx.room.Index;
import java.util.Objects;

@Entity(primaryKeys = {"politician_id", "election_id"},
        indices = {@Index(value = {"election_id"})})
public class Politician_Election {
    public int politician_id;
    public int election_id;
    public String position;

    public Politician_Election(int politician_id, int election_id, String position) {
        this.politician_id = politician_id;
        this.election_id = election_id;
        this.position = position;
    }

    /*@Override
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
    }*/
}
