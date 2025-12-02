package com.example.voteinformed.database.article;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "article")
public class Article {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="article_id")
    private int article_id;

    public Article ()
    {

    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    @NonNull
    @Override
    public String toString() {
        return "Article{" +
                "article_id=" + article_id +
                '}';
    }
}
