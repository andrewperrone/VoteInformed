package com.example.voteinformed.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.Election;
import com.example.voteinformed.data.entity.Issue;
import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.entity.User;
import com.example.voteinformed.data.entity.relation.User_Article;
import com.example.voteinformed.data.entity.relation.User_Election;
import com.example.voteinformed.data.entity.relation.User_Issue;
import com.example.voteinformed.data.entity.relation.User_Politician;
import com.example.voteinformed.data.entity.relation.UserWithArticles;
import com.example.voteinformed.data.entity.relation.UserWithElections;
import com.example.voteinformed.data.entity.relation.UserWithIssues;
import com.example.voteinformed.data.entity.relation.UserWithPoliticians;

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

    //Relations

    // UserArticle
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void linkUserToArticle(User_Article userArticle);
    @Query("DELETE FROM User_Article WHERE user_id = :userId AND article_id = :articleId")
    void unlinkUserFromArticle(int userId, int articleId);
    @Query("SELECT a.* FROM article a INNER JOIN User_Article ua ON a.article_id = ua.article_id WHERE ua.user_id = :userId AND a.article_title LIKE '%' || :query || '%' ")
    LiveData<List<Article>> searchArticlesForUser(int userId, String query);

    // User/Election
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void linkUserToElection(User_Election userElection);
    @Query("DELETE FROM User_Election WHERE user_id = :userId AND election_id = :electionId")
    void unlinkUserFromElection(int userId, int electionId);
    @Query("SELECT e.* FROM election e INNER JOIN User_Election ue ON e.election_id = ue.election_id WHERE ue.user_id = :userId AND e.election_name LIKE '%' || :query || '%' ")
    LiveData<List<Election>> searchElectionsForUser(int userId, String query);

    // User/Issue
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void linkUserToIssue(User_Issue userIssue);
    @Query("DELETE FROM User_Issue WHERE user_id = :userId AND issue_id = :issueId")
    void unlinkUserFromIssue(int userId, int issueId);
    @Query("SELECT i.* FROM issue i INNER JOIN User_Issue ui ON i.issue_id = ui.issue_id WHERE ui.user_id = :userId AND i.title LIKE '%' || :query || '%' ")
    LiveData<List<Issue>> searchIssuesForUser(int userId, String query);

    // User/Politician
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void linkUserToPolitician(User_Politician userPolitician);
    @Query("DELETE FROM User_Politician WHERE user_id = :userId AND politician_id = :politicianId")
    void unlinkUserFromPolitician(int userId, int politicianId);
    @Query("SELECT p.* FROM politician p INNER JOIN User_Politician up ON p.politician_id = up.politician_id WHERE up.user_id = :userId AND p.politician_name LIKE '%' || :query || '%' ")
    LiveData<List<Politician>> searchPoliticiansForUser(int userId, String query);

    @Transaction
    @Query("SELECT * FROM user WHERE user_id = :id")
    public LiveData<UserWithArticles> getUserWithArticles(int id);

    @Transaction
    @Query("SELECT * FROM user WHERE user_id = :id")
    public LiveData<UserWithElections> getUserWithElections(int id);

    @Transaction
    @Query("SELECT * FROM user WHERE user_id = :id")
    public LiveData<UserWithIssues> getUserWithIssues(int id);

    @Transaction
    @Query("SELECT * FROM user WHERE user_id = :id")
    public LiveData<UserWithPoliticians> getUserWithPoliticians(int id);
}
