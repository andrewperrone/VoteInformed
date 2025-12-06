package com.example.voteinformed.data.entity.relation;

import androidx.room.Entity;

import java.util.Objects;

@Entity(primaryKeys = {"user_id", "article_id"})
public class User_Article {
    private int user_id;
    private int article_id;

    public User_Article(int user_id, int article_id) {
        this.user_id = user_id;
        this.article_id = article_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    @Override
    public String toString() {
        return "User_Article{" +
                "user_id=" + user_id +
                ", article_id=" + article_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User_Article that = (User_Article) o;
        return user_id == that.user_id && article_id == that.article_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, article_id);
    }
}
