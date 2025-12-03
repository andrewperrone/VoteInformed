package com.example.voteinformed.ui.search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.repository.VoteInformed_Repository;

import java.util.List;
import java.util.Objects;

public class SearchViewModel extends AndroidViewModel {

    private final VoteInformed_Repository repository;

    // 사용자 입력(검색창)
    private final MutableLiveData<String> _searchQuery = new MutableLiveData<>();

    // 필터 상태 (정당)
    private final MutableLiveData<String> _partyFilter = new MutableLiveData<>();

    // 통합 파라미터 LiveData
    private final LiveData<SearchParameters> _searchParameters;

    // 최종 검색 결과 UI 노출용
    public final LiveData<List<Politician>> searchResults;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        this.repository = new VoteInformed_Repository(application);

        // 초기값
        _searchQuery.setValue(null);
        _partyFilter.setValue(null);

        _searchParameters = new SearchParametersLiveData(_searchQuery, _partyFilter);

        // 파라미터 변하면 repository.searchPoliticiansFiltered 호출
        searchResults = Transformations.switchMap(_searchParameters, params ->
                repository.searchPoliticiansFiltered(params.query, params.party)
        );
    }

    public void setSearchQuery(String query) {
        String normalizedQuery = (query == null || query.trim().isEmpty()) ? null : query.trim();
        if (!Objects.equals(_searchQuery.getValue(), normalizedQuery)) {
            _searchQuery.setValue(normalizedQuery);
        }
    }

    public void setPartyFilter(String party) {
        // pass null to clear filter
        String normalizedParty = (party == null || party.trim().isEmpty()) ? null : party.trim();
        if (!Objects.equals(_partyFilter.getValue(), normalizedParty)) {
            _partyFilter.setValue(normalizedParty);
        }
    }

    // MediatorLiveData to combine sources
    private static class SearchParametersLiveData extends MediatorLiveData<SearchParameters> {
        public SearchParametersLiveData(MutableLiveData<String> query, MutableLiveData<String> party) {
            setValue(new SearchParameters(query.getValue(), party.getValue()));

            addSource(query, q -> setValue(new SearchParameters(q, party.getValue())));
            addSource(party, p -> setValue(new SearchParameters(query.getValue(), p)));
        }
    }

    private static class SearchParameters {
        final String query;
        final String party;

        public SearchParameters(String query, String party) {
            this.query = query;
            this.party = party;
        }
    }
}
