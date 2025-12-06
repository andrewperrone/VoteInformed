package com.example.voteinformed.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voteinformed.R;
import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.ui.politician.PoliticianProfileActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private TextInputEditText inputSearch;
    private SearchViewModel viewModel;
    private PoliticianSearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Back 버튼
        findViewById(R.id.btnBackSearch).setOnClickListener(v -> finish());

        inputSearch = findViewById(R.id.inputSearch);

        // ViewModel
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        // RecyclerView
        RecyclerView recycler = findViewById(R.id.recyclerSearchResults);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PoliticianSearchAdapter(this, this::onPoliticianClicked);
        recycler.setAdapter(adapter);

        // 검색어 입력 - 키보드에서 검색 누를 때
        inputSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {

                performSearch();
                return true;
            }
            return false;
        });

        // Search 버튼
        MaterialButton btnApply = findViewById(R.id.btnApplySearch);
        btnApply.setOnClickListener(v -> performSearch());

        // Affiliation 필터
        ChipGroup groupAffiliations = findViewById(R.id.groupAffiliations);
        groupAffiliations.setOnCheckedStateChangeListener((group, checkedIds) -> {
            String filter = "";

            if (!checkedIds.isEmpty()) {
                int chipId = checkedIds.get(0);
                Chip chip = group.findViewById(chipId);
                if (chip != null) {
                    String text = chip.getText().toString();

                    if (text.equalsIgnoreCase("Republican")) {
                        filter = "Republican";
                    } else if (text.equalsIgnoreCase("Democratic")) {
                        filter = "Democratic";
                    } else if (text.equalsIgnoreCase("Independent")) {
                        filter = "Independent";
                    } else {
                        filter = "";
                    }
                }
            }

            viewModel.setFilter(filter);
            performSearch();
        });

        // 검색 결과 observe
        viewModel.getResults().observe(this, this::updateResults);
    }

    private void performSearch() {
        String query = "";
        if (inputSearch.getText() != null) {
            query = inputSearch.getText().toString().trim();
        }
        viewModel.search(query);
    }

    private void updateResults(List<Politician> list) {
        adapter.submitList(list);
    }

    private void onPoliticianClicked(Politician p) {
        Intent intent = new Intent(this, PoliticianProfileActivity.class);
        intent.putExtra("politician_id", p.getPolitician_id());
        startActivity(intent);
    }
}
