package com.example.voteinformed.data.entity.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.entity.Issue;

import java.util.List;

public class PoliticianWithIssues {

    @Embedded
    public Politician politician;

    @Relation(
            parentColumn = "politician_id",
            entityColumn = "issue_id",
            entity = Issue.class,
            associateBy = @Junction(value = Politician_Issue.class,
                    parentColumn = "politician_id",
                    entityColumn = "issue_id")
    )
    public List<Issue> issues;
}