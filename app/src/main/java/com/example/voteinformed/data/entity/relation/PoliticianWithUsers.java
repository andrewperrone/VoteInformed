package com.example.voteinformed.data.entity.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;
import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.entity.User;

import java.util.List;

public class PoliticianWithUsers {

    @Embedded
    public Politician politician;

    @Relation(
            parentColumn = "politician_id",
            entity = User.class,
            entityColumn = "user_id",
            associateBy = @Junction(
                    value = User_Politician.class,
                    parentColumn = "politician_id",
                    entityColumn = "user_id"
            )
    )
    public List<User> users;
}
