package com.example.voteinformed.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.voteinformed.data.entity.SavedArticle;

import java.util.List;

@Dao
public interface SavedArticle_Dao {

    // INSERT OR REPLACE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveArticle(SavedArticle savedArticle);

    // DELETE using entity
    @Delete
    void deleteSavedArticle(SavedArticle savedArticle);

    // DELETE using articleId
    @Query("DELETE FROM saved_articles WHERE articleId = :articleId")
    void removeSaved(String articleId);

    // CHECK if saved
    @Query("SELECT EXISTS(SELECT 1 FROM saved_articles WHERE articleId = :articleId)")
    boolean isArticleSaved(String articleId);

    // GET all saved articles
    @Query("SELECT * FROM saved_articles ORDER BY savedAt DESC")
    LiveData<List<SavedArticle>> getAllSaved();
}
