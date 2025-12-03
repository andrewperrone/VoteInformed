package com.example.voteinformed.data.entity;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "article")
public class Article {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="article_id")
    private int article_id;
    @ColumnInfo(name="article_title")
    private String article_title;
    @ColumnInfo(name="article_url")
    private String article_url;

    public Article(String article_title, String article_url) {
        this.article_title = article_title;
        this.article_url = article_url;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    @Override
    public String toString() {
        return "Article{" +
                "article_id=" + article_id +
                ", article_title='" + article_title + '\'' +
                ", article_url='" + article_url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return article_id == article.article_id && Objects.equals(article_title, article.article_title) && Objects.equals(article_url, article.article_url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(article_id, article_title, article_url);
    }
}
