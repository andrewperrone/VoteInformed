package com.example.voteinformed.data.entity.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;
import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.Politician;

import java.util.List;

public class PoliticianWithArticles {

    @Embedded
    public Politician politician;

    @Relation(
            parentColumn = "politician_id",
            entity = Article.class,
            entityColumn = "article_id",
            associateBy = @Junction(
                    value = Article_Politician.class,
                    parentColumn = "politician_id",
                    entityColumn = "article_id"
            )
    )
    public List<Article> articles;
}
