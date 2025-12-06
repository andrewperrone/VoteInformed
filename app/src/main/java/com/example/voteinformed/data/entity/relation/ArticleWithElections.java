package com.example.voteinformed.data.entity.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.Election;

import java.util.List;

public class ArticleWithElections {

    @Embedded
    public Article article;

    @Relation(
            parentColumn = "article_id",
            entityColumn = "election_id",
            associateBy = @Junction(Article_Election.class)
    )
    public List<Election> elections;
}