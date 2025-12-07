package com.example.voteinformed.data.entity.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;
import com.example.voteinformed.data.entity.Issue;
import com.example.voteinformed.data.entity.User;

import java.util.List;

public class IssueWithUsers {

    @Embedded
    public Issue issue;

    @Relation(
            parentColumn = "issue_id",
            entity = User.class,
            entityColumn = "user_id",
            associateBy = @Junction(
                    value = User_Issue.class,
                    parentColumn = "issue_id",
                    entityColumn = "user_id"
            )
    )
    public List<User> users;
}
