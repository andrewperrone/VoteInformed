package com.example.voteinformed.database.article;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "article_politician")
public class Article_Politician {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="article_politician_id")
    private int article_politician_id;

    public Article_Politician() {
    }

    public int getArticle_politician_id() {
        return article_politician_id;
    }

    @Override
    public String toString() {
        return "Article_Politician{" +
                "article_politician_id=" + article_politician_id +
                '}';
    }

    public void setArticle_politician_id(int article_politician_id) {
        this.article_politician_id = article_politician_id;
    }
}
