package com.example.voteinformed.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.Politician;
import java.util.List;

@Dao
public interface Politician_Dao {

    @Insert
    void insert(Politician politician);

    @Insert
    void insertAll(List<Politician> politicians);

    @Update
    void update(Politician politician);

    @Delete
    void delete(Politician politician);

    @Query("SELECT * FROM politician WHERE politician_id = :id")
    LiveData<Politician> getPoliticianById(int id);

    @Query("SELECT * FROM politician")
    LiveData<List<Politician>> getAllPoliticians();

    @Query("SELECT * FROM politician WHERE politician_name LIKE '%' || :query || '%' OR politician_party LIKE '%' || :query || '%'")
    LiveData<List<Politician>> searchPoliticians(String query);
}