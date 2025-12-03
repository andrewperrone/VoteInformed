package com.example.voteinformed.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.voteinformed.data.util.Converters;

import com.example.voteinformed.data.dao.Article_Dao;
import com.example.voteinformed.data.dao.Election_Dao;
import com.example.voteinformed.data.dao.Issue_Dao;
import com.example.voteinformed.data.dao.Politician_Dao;
import com.example.voteinformed.data.dao.User_Dao;
import com.example.voteinformed.data.entity.User;
import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.Issue;
import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.entity.Election;

// relations table
import com.example.voteinformed.data.entity.relation.User_Article;
import com.example.voteinformed.data.entity.relation.User_Issue;
import com.example.voteinformed.data.entity.relation.User_Election;
import com.example.voteinformed.data.entity.relation.User_Politician;
import com.example.voteinformed.data.entity.relation.Article_Issue;
import com.example.voteinformed.data.entity.relation.Article_Election;
import com.example.voteinformed.data.entity.relation.Article_Politician;

@Database(
        entities = {
                User.class,
                Article.class,
                Issue.class,
                Politician.class,
                Election.class,

                // relations table
                User_Article.class,
                User_Issue.class,
                User_Election.class,
                User_Politician.class,
                Article_Issue.class,
                Article_Election.class,
                Article_Politician.class
        },
        version = 1,
        exportSchema = false
)
@TypeConverters({Converters.class})
public abstract class VoteInformed_Database extends RoomDatabase {

    public abstract User_Dao userDao();
    public abstract Article_Dao articleDao();
    public abstract Issue_Dao issueDao();
    public abstract Election_Dao electionDao();
    public abstract Politician_Dao politicianDao();
}