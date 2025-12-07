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
import com.example.voteinformed.data.entity.relation.Article_Election;
import com.example.voteinformed.data.entity.relation.Article_Issue;
import com.example.voteinformed.data.entity.relation.Article_Politician;
import com.example.voteinformed.data.entity.relation.User_Article;
import com.example.voteinformed.data.entity.relation.ArticleWithElections;
import com.example.voteinformed.data.entity.relation.ArticleWithIssues;
import com.example.voteinformed.data.entity.relation.ArticleWithPoliticians;
import com.example.voteinformed.data.entity.relation.ArticleWithUsers;

import java.util.List;

@Dao
public interface Article_Dao {

    @Insert
    void insert(Article article);

    @Insert
    void insertAll(List<Article> articles);

    @Update
    void update(Article article);

    @Delete
    void delete(Article article);

    @Query("SELECT * FROM article WHERE article_id = :id")
    LiveData<Article> getArticleById(int id);

    @Query("SELECT * FROM article")
    LiveData<List<Article>> getAllArticles();

    @Query("SELECT * FROM article WHERE (article_title LIKE '%' || :query || '%' " + "AND (:filter IS NULL OR :filter = '' OR article_url = :filter))")
    LiveData<List<Article>> searchArticles(String query,String filter);
    
    //Relations
    // Article/Election
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void linkArticleToElection(Article_Election articleElection);
    @Query("DELETE FROM Article_Election WHERE article_id = :articleId AND election_id = :electionId")
    void unlinkArticleFromElection(int articleId, int electionId);
    @Query("SELECT e.* FROM election e INNER JOIN Article_Election ae ON e.election_id = ae.election_id WHERE ae.article_id = :articleId AND e.election_name LIKE '%' || :query || '%'")
    LiveData<List<Election>> searchElectionsInArticle(int articleId, String query);
    @Transaction
    @Query("SELECT * FROM article WHERE article_id = :id")
    LiveData<ArticleWithElections> getArticleWithElections(int id);

    // Article/Issue
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void linkArticleToIssue(Article_Issue articleIssue);
    @Query("DELETE FROM Article_Issue WHERE article_id = :articleId AND issue_id = :issueId")
    void unlinkArticleFromIssue(int articleId, int issueId);
    @Query("SELECT i.* FROM issue i INNER JOIN Article_Issue ai ON i.issue_id = ai.issue_id WHERE ai.article_id = :articleId AND i.title LIKE '%' || :query || '%'")
    LiveData<List<Issue>> searchIssuesInArticle(int articleId, String query);
    @Transaction
    @Query("SELECT * FROM article WHERE article_id = :id")
    LiveData<ArticleWithIssues> getArticleWithIssues(int id);

    // Article /Politician
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void linkArticleToPolitician(Article_Politician articlePolitician);
    @Query("DELETE FROM Article_Politician WHERE article_id = :articleId AND politician_id = :politicianId")
    void unlinkArticleFromPolitician(int articleId, int politicianId);
    @Query("SELECT p.* FROM politician p INNER JOIN Article_Politician ap ON p.politician_id = ap.politician_id WHERE ap.article_id = :articleId AND p.politician_name LIKE '%' || :query || '%'")
    LiveData<List<Politician>> searchPoliticiansInArticle(int articleId, String query);
    @Transaction
    @Query("SELECT * FROM article WHERE article_id = :id")
    LiveData<ArticleWithPoliticians> getArticleWithPoliticians(int id);

    // Article/User
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void linkArticleToUser(User_Article userArticle);
    @Query("DELETE FROM User_Article WHERE article_id = :articleId AND user_id = :userId")
    void unlinkArticleFromUser(int articleId, int userId);
    @Query("SELECT u.* FROM user u INNER JOIN User_Article ua ON u.user_id = ua.user_id WHERE ua.article_id = :articleId AND u.name LIKE '%' || :query || '%'")
    LiveData<List<User>> searchUsersWithArticle(int articleId, String query);
    @Transaction
    @Query("SELECT * FROM article WHERE article_id = :id")
    LiveData<ArticleWithUsers> getArticleWithUsers(int id);

}
