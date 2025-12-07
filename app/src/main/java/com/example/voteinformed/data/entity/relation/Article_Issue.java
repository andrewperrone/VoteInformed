package com.example.voteinformed.data.entity.relation;

import androidx.room.Entity;
import androidx.room.Index;
import java.util.Objects;

@Entity(primaryKeys = {"article_id", "issue_id"},
        indices = {@Index(value = {"issue_id"})})
public class Article_Issue {
    public int article_id;
    public int issue_id;

    public Article_Issue(int article_id, int issue_id) {
        this.article_id = article_id;
        this.issue_id = issue_id;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article_Issue that = (Article_Issue) o;
        return article_id == that.article_id && issue_id == that.issue_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(article_id, issue_id);
    }

    @Override
    public String toString() {
        return "Article_Issue{" +
                "article_id=" + article_id +
                ", issue_id=" + issue_id +
                '}';
    }*/
}
