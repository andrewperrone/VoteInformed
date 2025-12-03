package com.example.voteinformed.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.Issue;
import com.example.voteinformed.data.entity.Politician;

import java.util.List;

@Dao
public interface Issue_Dao {

    @Insert
    void insert(Issue issue);

    @Insert
    void insertAll(List<Issue> issues);

    @Update
    void update(Issue issue);

    @Delete
    void delete(Issue issue);

    @Query("SELECT * FROM issue WHERE issue_id = :id")
    LiveData<Issue> getIssueById(int id);

    @Query("SELECT * FROM issue")
    LiveData<List<Issue>> getAllIssues();

    @Query("SELECT * FROM issue WHERE (issue_id LIKE '%' || :query || '%' " + "AND :filter IS NULL OR :filter = '')")
    LiveData<List<Issue>> searchIssues(String query,String filter);
}

