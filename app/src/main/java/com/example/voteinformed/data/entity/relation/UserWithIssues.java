package com.example.voteinformed.data.entity.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.voteinformed.data.entity.User;
import com.example.voteinformed.data.entity.Issue;

import java.util.List;

public class UserWithIssues {

    @Embedded
    public User user;

    @Relation(
            parentColumn = "user_id",
            entityColumn = "issue_id",
            entity = Issue.class,
            associateBy = @Junction(value = User_Issue.class,
                    parentColumn = "user_id",
                    entityColumn = "issue_id")
    )
    public List<Issue> issues;
}