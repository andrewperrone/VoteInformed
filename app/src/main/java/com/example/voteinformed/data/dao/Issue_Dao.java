package com.example.voteinformed.data.dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.voteinformed.data.entity.Issue;

@Dao
public interface Issue_Dao {

    @Insert
    Long insertIssue(Issue issue);
    @Update
    void updateIssue(Issue issue);
    @Delete
    void deleteIssue(Issue issue);
}
