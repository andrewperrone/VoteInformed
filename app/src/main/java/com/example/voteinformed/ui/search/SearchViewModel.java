package com.example.voteinformed.ui.search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.repository.VoteInformed_Repository;

import java.util.List;

public class SearchViewModel extends AndroidViewModel {

    private final VoteInformed_Repository repo;

    // This LiveData holds the list of politicians to show in the UI
    private final MediatorLiveData<List<Politician>> results = new MediatorLiveData<>();
    private LiveData<List<Politician>> currentSource;

    private String currentFilter = "";

    public SearchViewModel(@NonNull Application application) {
        super(application);
        repo = new VoteInformed_Repository(application);
    }

    public LiveData<List<Politician>> getResults() {
        return results;
    }

    public void setFilter(String filter) {
        this.currentFilter = filter != null ? filter : "";
    }

    public void search(String query) {
        // Detach the old search results (if any)
        if (currentSource != null) {
            results.removeSource(currentSource);
        }

        // Connect to database method
        currentSource = repo.searchPoliticians(query, currentFilter);

        // Update the UI whenever the database returns new data
        results.addSource(currentSource, data -> results.setValue(data));
    }
}