package com.example.voteinformed.data.entity.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;
import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.User;

import java.util.List;

public class ArticleWithUsers {

    @Embedded
    public Article article;

    @Relation(
            parentColumn = "article_id",
            entity = User.class,
            entityColumn = "user_id",
            associateBy = @Junction(
                    value = User_Article.class,
                    parentColumn = "article_id",
                    entityColumn = "user_id"
            )
    )
    public List<User> users;
}
