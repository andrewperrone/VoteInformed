package com.example.voteinformed.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.OnConflictStrategy;

import com.example.voteinformed.data.entity.Committee;

import java.util.List;

@Dao
public interface Committee_Dao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Committee> committees);

    @Query("SELECT * FROM Committee")
    List<Committee> getAll();
}
