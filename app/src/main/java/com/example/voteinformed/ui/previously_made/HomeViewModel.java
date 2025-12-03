package com.example.voteinformed.ui.previously_made;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.voteinformed.Article;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private final MutableLiveData<List<Article>> savedArticles = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Article>> getSavedList() {
        return savedArticles;
    }

    public void toggleSaved(Article article) {
        List<Article> currentList = new ArrayList<>(savedArticles.getValue() != null ? savedArticles.getValue() : new ArrayList<>());
        if (isArticleSaved(article)) {
            currentList.remove(article);
        } else {
            currentList.add(article);
        }
        savedArticles.setValue(currentList);
    }

    public boolean isArticleSaved(Article article) {
        List<Article> currentList = savedArticles.getValue();
        return currentList != null && currentList.contains(article);
    }
}
