package com.example.voteinformed.data.entity.relation;
import androidx.room.Entity;

import java.util.Objects;

@Entity(primaryKeys = {"article_id", "election_id"})//(tableName = "article_election")
public class Article_Election {

    private int article_id;
    private int election_id;

    public Article_Election(int article_id, int election_id) {
        this.article_id = article_id;
        this.election_id = election_id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getElection_id() {
        return election_id;
    }

    public void setElection_id(int election_id) {
        this.election_id = election_id;
    }

    @Override
    public String toString() {
        return "Article_Election{" +
                "article_id=" + article_id +
                ", election_id=" + election_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article_Election that = (Article_Election) o;
        return article_id == that.article_id && election_id == that.election_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(article_id, election_id);
    }
}


