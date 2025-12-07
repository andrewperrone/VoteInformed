package com.example.voteinformed.data.entity.relation;
import androidx.room.Entity;
import androidx.room.Index;
import java.util.Objects;

@Entity(primaryKeys = {"user_id", "politician_id"},
        indices = {@Index(value = {"politician_id"})})
public class User_Politician {
    public int user_id;
    public int politician_id;

    public User_Politician(int user_id, int politician_id){
        this.user_id = user_id;
        this.politician_id = politician_id;
    }
/*
    @Override
    public String toString() {
        return "User_Politician{" +
                "user_id=" + user_id +
                ", politician_id=" + politician_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User_Politician that = (User_Politician) o;
        return user_id == that.user_id && politician_id == that.politician_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, politician_id);
    }*/
}
