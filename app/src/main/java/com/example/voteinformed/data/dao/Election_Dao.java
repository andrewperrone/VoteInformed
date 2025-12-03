package com.example.voteinformed.data.dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.Election;
import java.util.List;

@Dao
public interface Election_Dao {

    @Insert
    void insert(Election election);

    @Insert
    void insertAll(List<Election> election);

    @Update
    void update(Election election);

    @Delete
    void delete(Election election);

    @Query("SELECT * FROM election")
    List<Election> getAllElections();

    @Query("SELECT * FROM election WHERE election_id = :id")
    Election getElectionById(int id);

    @Query("SELECT * FROM election")
    LiveData<List<Election>> getAllAElectionLive();
}