package com.example.voteinformed.database.article;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "article_issue")
public class Article_Issue {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="article_issue_id")
    private int article_issue_id;

    public Article_Issue() {
    }

    public int getArticle_issue_id() {return article_issue_id;}

    @NonNull
    @Override
    public String toString() {
        return "Article_Issue{" +
                "article_issue_id=" + article_issue_id +
                '}';
    }

    public void setArticle_issue_id(int article_issue_id) {
        this.article_issue_id = article_issue_id;
    }
}
