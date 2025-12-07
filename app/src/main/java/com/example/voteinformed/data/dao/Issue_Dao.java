package com.example.voteinformed.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.Issue;
import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.entity.User;
import com.example.voteinformed.data.entity.relation.Article_Issue;
import com.example.voteinformed.data.entity.relation.IssueWithArticles;
import com.example.voteinformed.data.entity.relation.IssueWithPoliticians;
import com.example.voteinformed.data.entity.relation.IssueWithUsers;
import com.example.voteinformed.data.entity.relation.Politician_Issue;
import com.example.voteinformed.data.entity.relation.User_Issue;

import java.util.List;

@Dao
public interface Issue_Dao {

    @Insert
    void insert(Issue issue);

    @Insert
    void insertAll(List<Issue> issues);

    @Update
    void update(Issue issue);

    @Delete
    void delete(Issue issue);

    @Query("SELECT * FROM issue WHERE issue_id = :id")
    LiveData<Issue> getIssueById(int id);

    @Query("SELECT * FROM issue")
    LiveData<List<Issue>> getAllIssues();

    @Query("SELECT * FROM issue WHERE title LIKE '%' || :query || '%'")
    LiveData<List<Issue>> searchIssues(String query);

    //Relations
    // Issue/Politician
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void linkIssueToPolitician(Politician_Issue politicianIssue);
    @Query("DELETE FROM Politician_Issue WHERE issue_id = :issueId AND politician_id = :politicianId")
    void unlinkIssueFromPolitician(int issueId, int politicianId);
    @Query("SELECT p.* FROM politician p INNER JOIN Politician_Issue pi ON p.politician_id = pi.politician_id WHERE pi.issue_id = :issueId AND p.politician_name LIKE '%' || :query || '%'")
    LiveData<List<Politician>> searchPoliticiansWithIssue(int issueId, String query);

    // Issue/Article
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void linkIssueToArticle(Article_Issue articleIssue);
    @Query("DELETE FROM Article_Issue WHERE issue_id = :issueId AND article_id = :articleId")
    void unlinkIssueFromArticle(int issueId, int articleId);
    @Query("SELECT a.* FROM article a INNER JOIN Article_Issue ai ON a.article_id = ai.article_id WHERE ai.issue_id = :issueId AND a.article_title LIKE '%' || :query || '%'")
    LiveData<List<Article>> searchArticlesWithIssue(int issueId, String query);

    // Issue/User
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void linkIssueToUser(User_Issue userIssue);
    @Query("DELETE FROM User_Issue WHERE issue_id = :issueId AND user_id = :userId")
    void unlinkIssueFromUser(int issueId, int userId);
    @Query("SELECT u.* FROM user u INNER JOIN User_Issue ui ON u.user_id = ui.user_id WHERE ui.issue_id = :issueId AND u.name LIKE '%' || :query || '%'")
    LiveData<List<User>> searchUsersWithIssue(int issueId, String query);

    @Transaction
    @Query("SELECT * FROM issue WHERE issue_id = :id")
    public LiveData<IssueWithPoliticians> getIssueWithPoliticians(int id);

    @Transaction
    @Query("SELECT * FROM issue WHERE issue_id = :id")
    public LiveData<IssueWithArticles> getIssueWithArticles(int id);

    @Transaction
    @Query("SELECT * FROM issue WHERE issue_id = :id")
    public LiveData<IssueWithUsers> getIssueWithUsers(int id);
}
