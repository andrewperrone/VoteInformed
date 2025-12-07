package com.example.voteinformed.data.entity.relation;
import androidx.room.Entity;
import androidx.room.Index;
import java.util.Objects;

@Entity(primaryKeys = {"user_id", "issue_id"},
        indices = {@Index(value = {"issue_id"})})
public class User_Issue {
    public int user_id;
    public int issue_id;

    public User_Issue(int user_id, int issue_id) {
        this.user_id = user_id;
        this.issue_id = issue_id;
    }

    /*@Override
    public String toString() {
        return "User_Issue{" +
                "user_id=" + user_id +
                ", issue_id=" + issue_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User_Issue userIssue = (User_Issue) o;
        return user_id == userIssue.user_id && issue_id == userIssue.issue_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, issue_id);
    }*/
}
