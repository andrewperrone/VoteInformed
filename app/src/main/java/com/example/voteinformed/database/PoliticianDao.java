package com.example.voteinformed.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface PoliticianDao
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPolitician(Politician politician);

    @Delete
    void deletePolitician(Politician politician);

    @Query("SELECT * FROM politician_table ORDER BY politician_name ASC")
    LiveData<List<Politician>> getAllPoliticians();
}
