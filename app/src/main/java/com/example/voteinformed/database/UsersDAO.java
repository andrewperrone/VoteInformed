package com.example.voteinformed.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UsersDAO {
    @Insert
    void insert(Users user);

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    Users getUser(String username, String password);

    @Query("SELECT * FROM users WHERE username = :username")
    Users getUserByUsername(String username);

    @Query("SELECT * FROM users WHERE userEmail = :email")
    Users getUserByEmail(String email);
}
