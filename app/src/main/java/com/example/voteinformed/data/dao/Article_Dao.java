package com.example.voteinformed.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import androidx.room.Delete;
import com.example.voteinformed.data.entity.Article;
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

    @Transaction
    @Query("SELECT * FROM article WHERE article_id = :articleId")
    LiveData<List<Article>> getArticleWithIssues(int articleId);
}