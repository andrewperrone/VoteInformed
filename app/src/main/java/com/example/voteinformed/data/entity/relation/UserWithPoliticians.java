package com.example.voteinformed.data.entity.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.voteinformed.data.entity.User;
import com.example.voteinformed.data.entity.Politician;

import java.util.List;

public class UserWithPoliticians {

    @Embedded
    public User user;

    @Relation(
            parentColumn = "userId",
            entityColumn = "politician_id",
            associateBy = @Junction(User_Politician.class)
    )
    public List<Politician> politicians;
}