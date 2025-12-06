package com.example.voteinformed.ui.home;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.Election;
import com.example.voteinformed.data.entity.Issue;
import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.database.VoteInformed_Database;
import com.example.voteinformed.data.dao.Politician_Dao;
import com.example.voteinformed.data.repository.VoteInformed_Repository;
import com.example.voteinformed.data.repository.PoliticianNetworkRepository;

import java.util.List;

public class HomescreenViewModel extends AndroidViewModel {

    // Primary data access points
    private final VoteInformed_Repository repository;
    private final PoliticianNetworkRepository networkRepository; // Dedicated network tool

    // LiveData fields for the UI to observe
    // These streams fetch data directly from the local Room database
    public final LiveData<List<Politician>> allPoliticians;
    public final LiveData<List<Article>> allArticles;
    public final LiveData<List<Election>> allElections;
    public final LiveData<List<Issue>> allIssues;


    public HomescreenViewModel(Application application) {
        super(application);

        // Initialize the main local data repository
        repository = new VoteInformed_Repository(application);

        // Initialize LiveData from the main repository (Room Database)
        allPoliticians = repository.getAllPoliticians();
        allArticles = repository.getAllArticles();
        allElections = repository.getAllElections();
        allIssues = repository.getAllIssues();

        // Initialize the Network Repository to handle fetching
        Politician_Dao politicianDao = VoteInformed_Database.getInstance(application).politicianDao();
        networkRepository = new PoliticianNetworkRepository(politicianDao);

        // Trigger the network fetch and save to Room on startup
        fetchLatestPoliticianData();
    }

    private void fetchLatestPoliticianData() {
        networkRepository.fetchAndSaveCouncilMembers();
        networkRepository.fetchAndSaveStateLegislators();
    }

    public LiveData<List<Politician>> searchPoliticians(String query, String filter) {
        return repository.searchPoliticians(query, filter);
    }
}