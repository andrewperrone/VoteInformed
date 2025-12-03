package com.example.voteinformed.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.User;
import java.util.List;

@Dao
public interface User_Dao {

    @Insert
    void insert(User user);

    @Insert
    void insertAll(List<User> users);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM User")
    List<User> getAllUsers();

    @Query("SELECT * FROM User WHERE user_id = :id")
    User getUserById(int id);

    /*@Transaction
    @Query("SELECT * FROM User WHERE userId = :id")
    UserWithArticles getUserWithArticles(int id);*/

}