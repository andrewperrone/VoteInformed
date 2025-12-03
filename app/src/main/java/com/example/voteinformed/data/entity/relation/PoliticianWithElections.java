package com.example.voteinformed.data.entity.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.entity.Election;

import java.util.List;

public class PoliticianWithElections {

    @Embedded
    public Politician politician;

    @Relation(
            parentColumn = "politician_id",
            entityColumn = "election_id",
            associateBy = @Junction(Politician_Election.class)
    )
    public List<Election> electionss;
}