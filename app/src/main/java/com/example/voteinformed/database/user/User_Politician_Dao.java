package com.example.voteinformed.database.user;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;
import androidx.room.Update;

@Dao
public interface User_Politician_Dao {
    @Insert
    Long insertUserPolitician(User_Politician userPolitician);
    @Update
    void updateUserPolitician(User_Politician userPolitician);
    @Delete
    void deleteUserPolitician(User_Politician userPolitician);
}
