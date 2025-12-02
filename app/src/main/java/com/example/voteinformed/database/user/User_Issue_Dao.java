package com.example.voteinformed.database.user;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface User_Issue_Dao {
    @Insert
    Long insertUserIssue(User_Issue userIssue);
    @Update
    void updateUserIssue(User_Issue userIssue);
    @Delete
    void deleteUserIssue(User_Issue userIssue);
}
