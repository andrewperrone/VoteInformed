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
            parentColumn = "user_id",
            entityColumn = "politician_id",
            entity = Politician.class,
            associateBy = @Junction(value = User_Politician.class,
                    parentColumn = "user_id",
                    entityColumn = "politician_id")
    )
    public List<Politician> politicians;
}