package com.example.voteinformed.data.entity.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.voteinformed.data.entity.User;
import com.example.voteinformed.data.entity.Article;

import java.util.List;

public class UserWithArticles {

    @Embedded
    public User user;

    @Relation(
            parentColumn = "user_id",
            entityColumn = "article_id",
            associateBy = @Junction(User_Article.class)
    )
    public List<Article> articles;
}