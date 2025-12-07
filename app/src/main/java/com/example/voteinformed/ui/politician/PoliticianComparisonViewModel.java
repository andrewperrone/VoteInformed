package com.example.voteinformed.ui.politician;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.repository.VoteInformed_Repository;

import java.util.List;

public class PoliticianComparisonViewModel extends AndroidViewModel {

    private VoteInformed_Repository repository;

    private LiveData<List<Politician>> allPoliticians;
    private MutableLiveData<Politician> leftPolitician = new MutableLiveData<>();
    private MutableLiveData<Politician> rightPolitician = new MutableLiveData<>();

    public PoliticianComparisonViewModel(@NonNull Application application) {
        super(application);
        repository = new VoteInformed_Repository(application);
        allPoliticians = repository.getAllPoliticians();
    }


    public LiveData<List<Politician>> getAllPoliticians() {
        return allPoliticians;
    }

    public LiveData<Politician> getLeftPolitician() {
        return leftPolitician;
    }

    public LiveData<Politician> getRightPolitician() {
        return rightPolitician;
    }


    public void setLeftPolitician(Politician politician) {
        leftPolitician.setValue(politician);
    }

    public void setRightPolitician(Politician politician) {
        rightPolitician.setValue(politician);
    }


    public void setInitialPoliticians(List<Politician> politicians) {
        if (politicians != null && !politicians.isEmpty()) {
            if (leftPolitician.getValue() == null) {
                leftPolitician.setValue(politicians.get(0));
            }
            if (rightPolitician.getValue() == null && politicians.size() > 1) {
                rightPolitician.setValue(politicians.get(1));
            }
        }
    }

    public void setInitialById(int politicianId) {
        repository.getPoliticianById(politicianId)
                .observeForever(politician -> {
                    leftPolitician.setValue(politician);
                });
    }

    public void setRightById(int politicianId) {
        repository.getPoliticianById(politicianId)
                .observeForever(politician -> {
                    rightPolitician.setValue(politician);
                });
    }
}
