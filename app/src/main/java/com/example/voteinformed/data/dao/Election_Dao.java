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
import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.entity.User;
import com.example.voteinformed.data.entity.relation.Article_Election;
import com.example.voteinformed.data.entity.relation.ElectionWithArticles;
import com.example.voteinformed.data.entity.relation.ElectionWithPoliticians;
import com.example.voteinformed.data.entity.relation.ElectionWithUsers;
import com.example.voteinformed.data.entity.relation.Politician_Election;
import com.example.voteinformed.data.entity.relation.User_Election;

import java.util.List;

@Dao
public interface Election_Dao {

    @Insert
    void insert(Election election);

    @Insert
    void insertAll(List<Election> election);

    @Update
    void update(Election election);

    @Delete
    void delete(Election election);

    @Query("SELECT * FROM election WHERE election_id = :id")
    LiveData<Election> getElectionById(int id);

    @Query("SELECT * FROM election")
    LiveData<List<Election>> getAllElections();

    @Query("SELECT * FROM election WHERE election_name LIKE '%' || :query || '%'")
    LiveData<List<Election>> searchElections(String query);

    //Relations

    // Election/Politician
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void linkElectionToPolitician(Politician_Election politicianElection);

    @Query("DELETE FROM Politician_Election WHERE election_id = :electionId AND politician_id = :politicianId")
    void unlinkElectionFromPolitician(int electionId, int politicianId);

    @Query("SELECT p.* FROM politician p INNER JOIN Politician_Election pe ON p.politician_id = pe.politician_id WHERE pe.election_id = :electionId AND p.politician_name LIKE '%' || :query || '%'")
    LiveData<List<Politician>> searchPoliticiansInElection(int electionId, String query);

    @Transaction
    @Query("SELECT * FROM election WHERE election_id = :id")
    public LiveData<ElectionWithPoliticians> getElectionWithPoliticians(int id);

    // Election-Article
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void linkElectionToArticle(Article_Election articleElection);

    @Query("DELETE FROM Article_Election WHERE election_id = :electionId AND article_id = :articleId")
    void unlinkElectionFromArticle(int electionId, int articleId);

    @Query("SELECT a.* FROM article a INNER JOIN Article_Election ae ON a.article_id = ae.article_id WHERE ae.election_id = :electionId AND a.article_title LIKE '%' || :query || '%'")
    LiveData<List<Article>> searchArticlesWithElection(int electionId, String query);

    @Transaction
    @Query("SELECT * FROM election WHERE election_id = :id")
    public LiveData<ElectionWithArticles> getElectionWithArticles(int id);

    // Election/User
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void linkElectionToUser(User_Election userElection);

    @Query("DELETE FROM User_Election WHERE election_id = :electionId AND user_id = :userId")
    void unlinkElectionFromUser(int electionId, int userId);

    @Query("SELECT u.* FROM user u INNER JOIN User_Election ue ON u.user_id = ue.user_id WHERE ue.election_id = :electionId AND u.name LIKE '%' || :query || '%'")
    LiveData<List<User>> searchUsersInElection(int electionId, String query);

    @Transaction
    @Query("SELECT * FROM election WHERE election_id = :id")
    public LiveData<ElectionWithUsers> getElectionWithUsers(int id);
}

