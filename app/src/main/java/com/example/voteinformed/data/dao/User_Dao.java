package com.example.voteinformed.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.voteinformed.data.entity.User;
import com.example.voteinformed.data.entity.relation.UserWithArticles;

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

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("SELECT * FROM user WHERE user_id = :id")
    User getUserById(int id);

    @Transaction
    @Query("SELECT * FROM user WHERE user_id = :id")
    UserWithArticles getUserWithArticles(int id);

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUserLive();
}