package com.example.voteinformed.ui.home;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.repository.VoteInformed_Repository;

import java.util.List;

public class HomescreenViewModel extends AndroidViewModel {

    private final VoteInformed_Repository repository;

    public HomescreenViewModel(@NonNull Application application) {
        super(application);
        repository = new VoteInformed_Repository(application);
    }

    public LiveData<List<Article>> getAllArticles() {
        return repository.getAllArticles();
    }
}
