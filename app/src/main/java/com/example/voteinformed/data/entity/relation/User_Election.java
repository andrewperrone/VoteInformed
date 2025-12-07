package com.example.voteinformed.data.entity.relation;
import androidx.room.Entity;
import androidx.room.Index;
import java.util.Objects;

@Entity(primaryKeys = {"election_id", "user_id"},
        indices = {@Index(value = {"user_id"})})
public class User_Election{
    public int election_id;
    public int user_id;

    public User_Election(int election_id, int user_id) {
        this.election_id = election_id;
        this.user_id = user_id;
    }

    /*@Override
    public String toString() {
        return "User_Election{" +
                "election_id=" + election_id +
                ", user_id=" + user_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User_Election that = (User_Election) o;
        return election_id == that.election_id && user_id == that.user_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(election_id, user_id);
    }*/
}

