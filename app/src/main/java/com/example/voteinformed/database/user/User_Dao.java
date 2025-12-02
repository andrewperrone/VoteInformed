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
public interface User_Dao {
    @Insert //(onConflict = OnConflictStrategy.REPLACE)
    Long insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);


    /*@Query("SELECT*FROM user WHERE user_id = :userId")
    LiveData<User> getUserById(int userId);

    @Query("SELECT*FROM user")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT*FROM user WHERE email = :email LIMIT 1")
    LiveData<User> getUserByEmail(String email);*/

}

