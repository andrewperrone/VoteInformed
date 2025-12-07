package com.example.voteinformed.ui.politician;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.voteinformed.data.database.VoteInformed_Database;
import com.example.voteinformed.data.entity.Politician;

import java.util.List;

public class FeaturedPoliticianViewModel extends AndroidViewModel {

    private final LiveData<List<Politician>> randomPoliticians;

    public FeaturedPoliticianViewModel(@NonNull Application application) {
        super(application);

        VoteInformed_Database db =
                VoteInformed_Database.getInstance(application);

        randomPoliticians = db.politicianDao().getRandomPoliticians();
    }

    public LiveData<List<Politician>> getRandomPoliticians() {
        return randomPoliticians;
    }
}
