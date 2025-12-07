package com.example.voteinformed.data.entity.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;
import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.Issue;

import java.util.List;

public class IssueWithArticles {

    @Embedded
    public Issue issue;

    @Relation(
            parentColumn = "issue_id",
            entity = Article.class,
            entityColumn = "article_id",
            associateBy = @Junction(
                    value = Article_Issue.class,
                    parentColumn = "issue_id",
                    entityColumn = "article_id"
            )
    )
    public List<Article> articles;
}
