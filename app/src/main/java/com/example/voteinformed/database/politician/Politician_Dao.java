package com.example.voteinformed.database.politician;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Politician_Dao
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPolitician(Politician politician);

    @Update
    void updatePolitician(Politician politician);

    @Delete
    void deletePolitician(Politician politician);

    @Query("SELECT * FROM politician ORDER BY politician_name ASC")
    LiveData<List<Politician>> getAllPoliticians();
}
