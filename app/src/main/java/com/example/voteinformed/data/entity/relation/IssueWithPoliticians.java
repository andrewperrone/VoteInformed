package com.example.voteinformed.data.entity.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;
import com.example.voteinformed.data.entity.Issue;
import com.example.voteinformed.data.entity.Politician;

import java.util.List;

public class IssueWithPoliticians {

    @Embedded
    public Issue issue;

    @Relation(
            parentColumn = "issue_id",
            entity = Politician.class,
            entityColumn = "politician_id",
            associateBy = @Junction(
                    value = Politician_Issue.class,
                    parentColumn = "issue_id",
                    entityColumn = "politician_id"
            )
    )
    public List<Politician> politicians;
}
