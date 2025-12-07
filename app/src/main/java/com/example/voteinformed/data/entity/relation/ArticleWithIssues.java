package com.example.voteinformed.data.entity.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.Issue;

import java.util.List;

public class ArticleWithIssues {

    @Embedded
    public Article article;

    @Relation(
            parentColumn = "article_id",
            entityColumn = "issue_id",
            entity = Issue.class,
            associateBy = @Junction(value = Article_Issue.class,
                    parentColumn = "article_id",
                    entityColumn = "issue_id")
    )
    public List<Issue> issues;
}