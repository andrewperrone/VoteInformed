package com.example.voteinformed.data.entity.relation;
import androidx.room.Entity;

import java.util.Objects;

@Entity(primaryKeys = {"user_id", "issue_id"})//(tableName = "user_issue")
public class User_Issue {
    private int user_id;
    private int issue_id;

    public User_Issue(int user_id, int issue_id) {
        this.user_id = user_id;
        this.issue_id = issue_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(int issue_id) {
        this.issue_id = issue_id;
    }

    @Override
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
    }
}
