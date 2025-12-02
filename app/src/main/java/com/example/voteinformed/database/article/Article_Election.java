package com.example.voteinformed.database.article;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "article_election")
public class Article_Election {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="article_election_id")
    private int article_election_id;

    public Article_Election ()
    {

    }

    public int getArticle_election_id() {
        return article_election_id;
    }

    public void setArticle_election_id(int article_election_id) {
        this.article_election_id = article_election_id;
    }
}
