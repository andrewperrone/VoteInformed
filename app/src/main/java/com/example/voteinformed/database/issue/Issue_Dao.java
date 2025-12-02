package com.example.voteinformed.database.issue;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface Issue_Dao {

    @Insert
    Long insertIssue(Issue issue);
    @Update
    void updateIssue(Issue issue);
    @Delete
    void deleteIssue(Issue issue);
}
