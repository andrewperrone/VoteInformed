package com.example.voteinformed.data.entity.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.Election;

import java.util.List;

//TODO: should have toString methods etc
public class ArticleWithElections {

    @Embedded
    public Article article;

    @Relation(
            parentColumn = "article_id",
            entityColumn = "election_id",
            entity = Election.class,
            associateBy = @Junction(value = Article_Election.class,
                    parentColumn = "article_id",
                    entityColumn = "election_id")
    )
    public List<Election> elections;
}