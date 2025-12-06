package com.example.voteinformed.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "saved_articles")
public class SavedArticle {

    @PrimaryKey
    @NonNull
    public String articleId;

    public String title;
    public String summary;
    public String imageUrl;
    public long savedAt;

    public SavedArticle(@NonNull String articleId, String title, String summary, String imageUrl, long savedAt) {
        this.articleId = articleId;
        this.title = title;
        this.summary = summary;
        this.imageUrl = imageUrl;
        this.savedAt = savedAt;
    }

    @NonNull
    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(@NonNull String articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(long savedAt) {
        this.savedAt = savedAt;
    }

    @Override
    public String toString() {
        return "SavedArticle{" +
                "articleId='" + articleId + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", savedAt=" + savedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SavedArticle that = (SavedArticle) o;
        return savedAt == that.savedAt && Objects.equals(articleId, that.articleId) && Objects.equals(title, that.title) && Objects.equals(summary, that.summary) && Objects.equals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, title, summary, imageUrl, savedAt);
    }
}
