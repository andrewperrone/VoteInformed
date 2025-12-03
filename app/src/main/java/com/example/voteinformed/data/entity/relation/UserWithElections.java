package com.example.voteinformed.data.entity.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.voteinformed.data.entity.User;
import com.example.voteinformed.data.entity.Election;

import java.util.List;

public class UserWithElections {

    @Embedded
    public User user;

    @Relation(
            parentColumn = "user_id",
            entityColumn = "election_id",
            associateBy = @Junction(User_Election.class)
    )
    public List<Election> elections;
}