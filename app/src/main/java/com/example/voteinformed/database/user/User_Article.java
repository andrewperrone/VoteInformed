package com.example.voteinformed.database.user;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_article")
public class User_Article {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="user_article_id")
    private int user_article_id;

    public User_Article() {
    }

    public int getUser_article_id() {
        return user_article_id;
    }

    public void setUser_article_id(int user_article_id) {
        this.user_article_id = user_article_id;
    }
}
