package com.example.voteinformed.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.voteinformed.data.dao.Article_Dao;
import com.example.voteinformed.data.dao.Election_Dao;
import com.example.voteinformed.data.dao.Issue_Dao;
import com.example.voteinformed.data.dao.Politician_Dao;
import com.example.voteinformed.data.dao.User_Dao;
import com.example.voteinformed.data.database.VoteInformed_Database;
import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.Election;
import com.example.voteinformed.data.entity.Issue;
import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.entity.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VoteInformed_Repository {

    private final VoteInformed_Database voteInformed_db;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final Article_Dao articleDao;
    private final Election_Dao electionDao;
    private final Issue_Dao issueDao;
    private final Politician_Dao politicianDao;
    private final User_Dao userDao;

    public VoteInformed_Repository(Context context) {

        // NEEDS TO BE A SINGLE INSTANCE
        voteInformed_db = VoteInformed_Database.getInstance(context);

        articleDao = voteInformed_db.articleDao();
        electionDao = voteInformed_db.electionDao();
        issueDao = voteInformed_db.issueDao();
        politicianDao = voteInformed_db.politicianDao();
        userDao = voteInformed_db.userDao();
    }

    // Read opperators need to use LiveData

    public LiveData<List<Article>> getAllArticles() {
        return articleDao.getAllArticles();
    }

    public LiveData<List<Election>> getAllElections() {
        return electionDao.getAllElections();
    }

    public LiveData<List<Issue>> getAllIssues() {
        return issueDao.getAllIssues();
    }

    public LiveData<List<Politician>> getAllPoliticians() {
        return politicianDao.getAllPoliticians();
    }

    public LiveData<List<User>> getAllUsers() {
        return userDao.getAllUsers();
    }

    // Write opperators need to use executor

    public void insertArticle(Article article) {
        executor.execute(() -> articleDao.insert(article));
    }

    public void updateArticle(Article article) {
        executor.execute(() -> articleDao.update(article));
    }

    public void deleteArticle(Article article) {
        executor.execute(() -> articleDao.delete(article));
    }

    // Repeat similarly for other entities...
}
