package com.example.voteinformed.data.entity.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;
import com.example.voteinformed.data.entity.Election;
import com.example.voteinformed.data.entity.Politician;

import java.util.List;

public class ElectionWithPoliticians {

    @Embedded
    public Election election;

    @Relation(
            parentColumn = "election_id",
            entity = Politician.class,
            entityColumn = "politician_id",
            associateBy = @Junction(
                    value = Politician_Election.class,
                    parentColumn = "election_id",
                    entityColumn = "politician_id"
            )
    )
    public List<Politician> politicians;
}
