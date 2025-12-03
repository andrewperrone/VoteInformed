package com.example.voteinformed.data.entity.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.voteinformed.data.entity.Politician;

import java.util.List;

public class PoliticianWithIssues {

    @Embedded
    public Politician politician;

    @Relation(
            parentColumn = "politician_id",
            entityColumn = "issue_id",
            associateBy = @Junction(Politician_Issue.class)
    )
    public List<Politician> politicians;
}