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
public interface User_Election_Dao {

    @Insert
    Long insertUserElection(User_Election userElection);
    @Update
    void updateUserElection(User_Election userElection);
    @Delete
    void deleteUserElection(User_Election userElection);
}
