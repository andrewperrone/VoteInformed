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
import com.example.voteinformed.data.entity.relation.Article_Politician;
import com.example.voteinformed.data.entity.relation.PoliticianWithArticles;
import com.example.voteinformed.data.entity.relation.PoliticianWithUsers;
import com.example.voteinformed.data.entity.relation.Politician_Election;
import com.example.voteinformed.data.entity.relation.Politician_Issue;
import com.example.voteinformed.data.entity.relation.PoliticianWithElections;
import com.example.voteinformed.data.entity.relation.PoliticianWithIssues;
import com.example.voteinformed.data.entity.relation.User_Politician;

import java.util.List;

@Dao
public interface Politician_Dao {

    @Insert
    void insert(Politician politician);

    @Insert
    void insertAll(List<Politician> politicians);

    @Update
    void update(Politician politician);

    @Delete
    void delete(Politician politician);

    @Query("SELECT * FROM politician WHERE politician_id = :id")
    LiveData<Politician> getPoliticianById(int id);

    @Query("SELECT * FROM politician")
    LiveData<List<Politician>> getAllPoliticians();

    @Query("SELECT * FROM politician " +
            "WHERE (politician_name LIKE '%' || :query || '%' " +
            "OR politician_party LIKE '%' || :query || '%') " +
            "AND (:filter IS NULL OR :filter = '' OR politician_party = :filter)")
    LiveData<List<Politician>> searchPoliticians(String query,String filter);

    //relations

    // Politician/Issue
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void linkPoliticianToIssue(Politician_Issue politicianIssue);
    @Query("DELETE FROM Politician_Issue WHERE politician_id = :politicianId AND issue_id = :issueId")
    void unlinkPoliticianFromIssue(int politicianId, int issueId);
    @Query("SELECT i.* FROM issue i INNER JOIN Politician_Issue pi ON i.issue_id = pi.issue_id WHERE pi.politician_id = :politicianId AND i.title LIKE '%' || :query || '%'")
    LiveData<List<Issue>> searchIssuesWithPolitician(int politicianId, String query);
    @Transaction
    @Query("SELECT * FROM politician WHERE politician_id = :id")
    LiveData<PoliticianWithIssues> getPoliticianWithIssues(int id);


    // Politician/Election
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void linkPoliticianToElection(Politician_Election politicianElection);
    @Query("DELETE FROM Politician_Election WHERE politician_id = :politicianId AND election_id = :electionId")
    void unlinkPoliticianFromElection(int politicianId, int electionId);
    @Query("SELECT e.* FROM election e INNER JOIN Politician_Election pe ON e.election_id = pe.election_id WHERE pe.politician_id = :politicianId AND e.election_name LIKE '%' || :query || '%'")
    LiveData<List<Election>> searchElectionsWithPolitician(int politicianId, String query);
    @Transaction
    @Query("SELECT * FROM politician WHERE politician_id = :id")
    LiveData<PoliticianWithElections> getPoliticianWithElections(int id);

    // Politician/User
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void linkPoliticianToUser(User_Politician userPolitician);
    @Query("DELETE FROM User_Politician WHERE politician_id = :politicianId AND user_id = :userId")
    void unlinkPoliticianFromUser(int politicianId, int userId);
    @Query("SELECT u.* FROM user u INNER JOIN User_Politician up ON u.user_id = up.user_id WHERE up.politician_id = :politicianId AND u.name LIKE '%' || :query || '%'")
    LiveData<List<User>> searchUsersWithPolitician(int politicianId, String query);
    @Transaction
    @Query("SELECT * FROM politician WHERE politician_id = :id")
    LiveData<PoliticianWithUsers> getPoliticianWithUsers(int id);

    // Politician/Article
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void linkPoliticianToArticle(Article_Politician articlePolitician);
    @Query("DELETE FROM Article_Politician WHERE politician_id = :politicianId AND article_id = :articleId")
    void unlinkPoliticianFromArticle(int politicianId, int articleId);
    @Query("SELECT a.* FROM article a INNER JOIN Article_Politician ap ON a.article_id = ap.article_id WHERE ap.politician_id = :politicianId AND a.article_title LIKE '%' || :query || '%'")
    LiveData<List<Article>> searchArticlesWithPolitician(int politicianId, String query);
    @Transaction
    @Query("SELECT * FROM politician WHERE politician_id = :id")
    LiveData<PoliticianWithArticles> getPoliticianWithArticles(int id);
}
