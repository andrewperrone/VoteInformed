package com.example.voteinformed.data.repository;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.voteinformed.data.dao.Article_Dao;
import com.example.voteinformed.data.dao.Election_Dao;
import com.example.voteinformed.data.dao.Issue_Dao;
import com.example.voteinformed.data.dao.Politician_Dao;
import com.example.voteinformed.data.dao.SavedArticle_Dao;
import com.example.voteinformed.data.dao.User_Dao;
import com.example.voteinformed.data.database.VoteInformed_Database;
import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.Election;
import com.example.voteinformed.data.entity.Issue;
import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.entity.SavedArticle;
import com.example.voteinformed.data.entity.User;
import com.example.voteinformed.data.entity.relation.ArticleWithElections;
import com.example.voteinformed.data.entity.relation.ArticleWithIssues;
import com.example.voteinformed.data.entity.relation.ArticleWithPoliticians;
import com.example.voteinformed.data.entity.relation.ElectionWithPoliticians;
import com.example.voteinformed.data.entity.relation.PoliticianWithElections;
import com.example.voteinformed.data.entity.relation.PoliticianWithIssues;
import com.example.voteinformed.data.entity.relation.UserWithArticles;
import com.example.voteinformed.data.entity.relation.UserWithElections;
import com.example.voteinformed.data.entity.relation.UserWithIssues;
import com.example.voteinformed.data.entity.relation.UserWithPoliticians;

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
    private final SavedArticle_Dao savedArticleDao;


    public VoteInformed_Repository(Context context) {

        // NEEDS TO BE A SINGLE INSTANCE
        voteInformed_db = VoteInformed_Database.getInstance(context);

        articleDao = voteInformed_db.articleDao();
        electionDao = voteInformed_db.electionDao();
        issueDao = voteInformed_db.issueDao();
        politicianDao = voteInformed_db.politicianDao();
        userDao = voteInformed_db.userDao();
        savedArticleDao = voteInformed_db.savedArticleDao();

    }

    // Read operators need to use LiveData

    //getAll_
    public LiveData<List<User>> getAllUsers() {
        return userDao.getAllUsers();
    }
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
    public LiveData<List<SavedArticle>> getAllSavedArticles() {
        return savedArticleDao.getAllSaved();
    }

    public void saveArticle(SavedArticle article) {
        executor.execute(() -> savedArticleDao.saveArticle(article));
    }

    public void removeSaved(String articleId) {
        executor.execute(() -> savedArticleDao.removeSaved(articleId));
    }

    public boolean isArticleSaved(String articleId) {
        return savedArticleDao.isArticleSaved(articleId);
    }


    public LiveData<List<Politician>> searchPoliticiansFiltered(String query, String party) {
        MutableLiveData<List<Politician>> result = new MutableLiveData<>();
        // result.setValue(...);
        return result;
    }


    //get_ById
    public LiveData<User> getUserById(int id) {
        return userDao.getUserById(id);
    }
    public LiveData<Article> getArticleById(int id) {
        return articleDao.getArticleById(id);
    }
    public LiveData<Election> getElectionById(int id) {
        return electionDao.getElectionById(id);
    }
    public LiveData<Issue> getIssueById(int id) {
        return issueDao.getIssueById(id);
    }
    public LiveData<Politician> getPoliticianById(int id) {
        return politicianDao.getPoliticianById(id);
    }

    //Search
    public LiveData<List<Politician>> searchPoliticians(String query, String filter) {
        return politicianDao.searchPoliticians(query, filter);
    }

    public LiveData<List<Article>> searchArticles(String query, String filter) {
        return articleDao.searchArticles(query, filter);
    }
    public LiveData<List<Issue>> searchIssues(String query) {
        return issueDao.searchIssues(query);
    }

    // Write operators need to use executor
    //Article
    public void insertArticle(Article article) {
        executor.execute(() -> articleDao.insert(article));
    }
    public void updateArticle(Article article) {
        executor.execute(() -> articleDao.update(article));
    }
    public void deleteArticle(Article article) {
        executor.execute(() -> articleDao.delete(article));
    }
    //Election
    public void insertElection(Election election) {
        executor.execute(() -> electionDao.insert(election));
    }
    public void updateElection(Election election) {
        executor.execute(() -> electionDao.update(election));
    }
    public void deleteElection(Election election) {
        executor.execute(() -> electionDao.delete(election));
    }

    // Issue
    public void insertIssue(Issue issue) {
        executor.execute(() -> issueDao.insert(issue));
    }
    public void updateIssue(Issue issue) {
        executor.execute(() -> issueDao.update(issue));
    }
    public void deleteIssue(Issue issue) {
        executor.execute(() -> issueDao.delete(issue));
    }

    //Politician
    public void insertPolitician(Politician politician) {
        executor.execute(() -> politicianDao.insert(politician));
    }
    public void updatePolitician(Politician politician) {
        executor.execute(() -> politicianDao.update(politician));
    }
    public void deletePolitician(Politician politician) {
        executor.execute(() -> politicianDao.delete(politician));
    }

    //User
    public void insertUser(User user) {
        executor.execute(() -> userDao.insert(user));
    }
    public void updateUser(User user, UpdateCallback callback) {
        executor.execute(() -> {
            try {
                userDao.update(user);

                // Success: Notify UI on Main Thread
                new Handler(Looper.getMainLooper()).post(() -> {
                    if (callback != null) callback.onResult(true);
                });
            } catch (Exception e) {
                // Failure: Notify UI
                new Handler(Looper.getMainLooper()).post(() -> {
                    if (callback != null) callback.onResult(false);
                });
            }
        });
    }
    public void deleteUser(User user) {
        executor.execute(() -> userDao.delete(user));
    }

    //Politician With
    public LiveData<PoliticianWithIssues> getPoliticianWithIssues(int id) {
        return politicianDao.getPoliticianWithIssues(id);
    }

    public LiveData<PoliticianWithElections> getPoliticianWithElections(int id) {
        return politicianDao.getPoliticianWithElections(id);
    }

    // User With
    public LiveData<UserWithArticles> getUserWithArticles(int id) {
        return userDao.getUserWithArticles(id);
    }

    public LiveData<UserWithElections> getUserWithElections(int id) {
        return userDao.getUserWithElections(id);
    }

    public LiveData<UserWithIssues> getUserWithIssues(int id) {
        return userDao.getUserWithIssues(id);
    }

    public LiveData<UserWithPoliticians> getUserWithPoliticians(int id) {
        return userDao.getUserWithPoliticians(id);
    }

    //Article With
    public LiveData<ArticleWithIssues> getArticleWithIssues(int articleId) {
        return articleDao.getArticleWithIssues(articleId);
    }

    public LiveData<ArticleWithElections> getArticleWithElections(int articleId) {
        return articleDao.getArticleWithElections(articleId);
    }

    public LiveData<ArticleWithPoliticians> getArticleWithPoliticians(int articleId) {
        return articleDao.getArticleWithPoliticians(articleId);
    }

    // Election With
    public LiveData<ElectionWithPoliticians> getElectionWithPoliticians(int id) {
        return electionDao.getElectionWithPoliticians(id);
    }




    public interface LoginCallback{
        void onResult(User user);
    }

    public void login(String email, String password, LoginCallback callback) {
        executor.execute(() -> {
            User user = userDao.login(email, password);
            // Pass the user object back
            new Handler(Looper.getMainLooper()).post(() -> {
                callback.onResult(user);
            });
        });
    }

    public interface RegisterCallback{
        void onResult(boolean success, boolean emailAlreadyExists);
    }

    public interface UpdateCallback {
        void onResult(boolean success);
    }
    public void register(String email, String password, RegisterCallback callback) {
        executor.execute(()->
        {
            // check if email is taken
            User user = userDao.getUserByEmail(email);
            if (user != null)
            {
                new Handler(Looper.getMainLooper()).post(()->
                        callback.onResult(false, true)
                );
            }
            else
            {
                User newUser = new User(email, password);
                userDao.insert(newUser);
                new Handler(Looper.getMainLooper()).post(() ->
                        callback.onResult(true, false)
                );
            }

        });
    }

}
