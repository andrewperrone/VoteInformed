package com.example.voteinformed.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voteinformed.R;
import com.example.voteinformed.ui.politician.PoliticianProfileActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SearchActivity extends AppCompatActivity {

    private TextInputEditText inputSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Back button: closes current activity
        ImageButton btnBack = findViewById(R.id.btnBackSearch);
        btnBack.setOnClickListener(v -> finish());

        // Search input field
        inputSearch = findViewById(R.id.inputSearch);

        // Handle Enter key / IME search action
        inputSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    actionId == EditorInfo.IME_ACTION_DONE ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER &&
                            event.getAction() == KeyEvent.ACTION_DOWN)) {

                performSearch();
                return true;
            }
            return false;
        });

        // Search button click listener
        MaterialButton btnApplySearch = findViewById(R.id.btnApplySearch);
        btnApplySearch.setOnClickListener(v -> performSearch());
    }

    /**
     * Perform search based on user input
     * Currently only handles "obama" query
     */
    private void performSearch() {
        String query = inputSearch.getText() == null ? "" : inputSearch.getText().toString().trim();

        if (query.equalsIgnoreCase("obama")) {
            // Open politicians profile if query matches "obama"
            Intent intent = new Intent(SearchActivity.this, PoliticianProfileActivity.class);
            startActivity(intent);
        } else {
            // TODO: Show "No results found" or implement other search functionality
        }
    }
}
