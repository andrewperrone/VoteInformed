package com.example.voteinformed.data.entity.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;
import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.Election;

import java.util.List;

public class ElectionWithArticles {

    @Embedded
    public Election election;

    @Relation(
            parentColumn = "election_id",
            entity = Article.class,
            entityColumn = "article_id",
            associateBy = @Junction(
                    value = Article_Election.class,
                    parentColumn = "election_id",
                    entityColumn = "article_id"
            )
    )
    public List<Article> articles;
}
