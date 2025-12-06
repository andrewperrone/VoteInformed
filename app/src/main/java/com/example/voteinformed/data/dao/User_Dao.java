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

    @Query("SELECT * FROM user WHERE user_id = :id")
    LiveData<User> getUserById(int id);

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM user WHERE email = :email AND password = :password LIMIT 1")
    User login(String email, String password);

    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    User getUserByEmail(String email);
}