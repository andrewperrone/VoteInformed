package com.example.voteinformed.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Committee")
public class Committee {

    @PrimaryKey
    public int BodyID;

    public String BodyName;
    public String BodyTypeName;
    public int BodyActiveFlag;
}
