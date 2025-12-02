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
public interface User_Article_Dao {
    @Insert
    Long insertUserArticle(User_Article userArticle);
    @Update
    void updateUserArticle(User_Article userArticle);
    @Delete
    void deleteUserArticle(User_Article userArticle);
}
