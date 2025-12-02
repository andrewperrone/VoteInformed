package com.example.voteinformed.database.user;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "user_article")
public class User_Article {
    @ColumnInfo(name = "article_id")
    private int article_id;
    @ColumnInfo(name = "user_id")
    private int user_id;

    public User_Article(int article_id, int user_id){
        this.article_id = article_id;
        this.user_id = user_id;
    }

    public int getArticleId(){
        return article_id;
    }

    public void setArticleId(int article_id){
        this.article_id = article_id;
    }

    public int getUserId(){
        return user_id;
    }

    public void setUserId(int user_id){
        this.user_id = user_id;
    }

}
