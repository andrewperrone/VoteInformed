package com.example.voteinformed.ui.saved;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.voteinformed.data.entity.SavedArticle;
import com.example.voteinformed.data.repository.VoteInformed_Repository;

import java.util.List;

public class SavedArticleViewModel extends AndroidViewModel {

    private final VoteInformed_Repository repo;

    public LiveData<List<SavedArticle>> savedArticles;

    public SavedArticleViewModel(@NonNull Application app) {
        super(app);
        repo = new VoteInformed_Repository(app);
        savedArticles = repo.getAllSavedArticles();
    }

    public void save(SavedArticle article) {
        repo.saveArticle(article);
    }

    public void remove(String articleId) {
        repo.removeSaved(articleId);
    }
}
