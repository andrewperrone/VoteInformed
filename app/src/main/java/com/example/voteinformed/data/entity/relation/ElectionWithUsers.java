package com.example.voteinformed.data.entity.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;
import com.example.voteinformed.data.entity.Election;
import com.example.voteinformed.data.entity.User;

import java.util.List;

public class ElectionWithUsers {

    @Embedded
    public Election election;

    @Relation(
            parentColumn = "election_id",
            entity = User.class,
            entityColumn = "user_id",
            associateBy = @Junction(
                    value = User_Election.class,
                    parentColumn = "election_id",
                    entityColumn = "user_id"
            )
    )
    public List<User> users;
}
