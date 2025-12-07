package com.example.voteinformed.data.entity.relation;

import androidx.room.Entity;
import androidx.room.Index;

import java.util.Objects;

@Entity(primaryKeys = {"user_id", "article_id"},
        indices = {@Index(value = {"article_id"})})
public class User_Article {
    public int user_id;
    public int article_id;

    public User_Article(int user_id, int article_id) {
        this.user_id = user_id;
        this.article_id = article_id;
    }

    /*@Override
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
    }*/
}
