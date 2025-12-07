package com.example.voteinformed.data.entity.relation;

import androidx.room.Entity;
import androidx.room.Index;
import java.util.Objects;

@Entity(primaryKeys = {"article_id", "politician_id"},
        indices = {@Index(value = {"politician_id"})})
public class Article_Politician {
    public int article_id;
    public int politician_id;

    public Article_Politician(int article_id, int politician_id) {
        this.article_id = article_id;
        this.politician_id = politician_id;
    }

    /*@Override
    public String toString() {
        return "Article_Politician{" +
                "article_id=" + article_id +
                ", politician_id=" + politician_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article_Politician that = (Article_Politician) o;
        return article_id == that.article_id && politician_id == that.politician_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(article_id, politician_id);
    }*/
}
