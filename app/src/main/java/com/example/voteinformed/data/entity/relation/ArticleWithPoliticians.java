package com.example.voteinformed.data.entity.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.Politician;

import java.util.List;

public class ArticleWithPoliticians {

    @Embedded
    public Article article;

    @Relation(
            parentColumn = "article_id",
            entityColumn = "politician_id",
            associateBy = @Junction(Article_Politician.class)
    )
    public List<Politician> politicians;
}