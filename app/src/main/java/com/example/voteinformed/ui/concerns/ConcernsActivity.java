package com.example.voteinformed.ui.concerns;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voteinformed.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConcernsActivity extends AppCompatActivity {

    private ChipGroup chipGroup;
    private final List<ChipData> allChips = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "ConcernsPrefs";
    private static final String SELECTED_CHIPS_KEY = "SelectedChips";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concerns);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Close button
        ImageButton btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> finish());

        chipGroup = findViewById(R.id.chipGroupConcerns);

        // Load saved selections and initialize chips
        initializeChips();

        // Listener to handle check changes
        CompoundButton.OnCheckedChangeListener chipListener = (buttonView, isChecked) -> {
            String chipText = buttonView.getText().toString();
            updateChipStatus(chipText, isChecked);
            saveSelections();
            reorderChips();
        };

        // Add chips to ChipGroup (already sorted)
        for (ChipData data : allChips) {
            chipGroup.addView(createChip(data, chipListener));
        }
    }

    /**
     * Initialize chips based on saved selections
     * Chips are sorted: selected first, then unselected
     */
    private void initializeChips() {
        Set<String> savedSelections = sharedPreferences.getStringSet(SELECTED_CHIPS_KEY, new HashSet<>());

        // Define all chip texts
        String[] chipTexts = {
                "Civil Rights",
                "Healthcare",
                "Infrastructure",
                "Transportation",
                "Jobs",
                "Environment",
                "Military",
                "Economy",
                "Crime",
                "Taxes",
                "Privacy",
                "Education"
        };

        // Create two lists: selected and unselected
        List<ChipData> selectedChips = new ArrayList<>();
        List<ChipData> unselectedChips = new ArrayList<>();

        for (String text : chipTexts) {
            boolean selected = savedSelections.contains(text);
            ChipData chipData = new ChipData(text, selected);

            if (selected) {
                selectedChips.add(chipData);
            } else {
                unselectedChips.add(chipData);
            }
        }

        // Add selected chips first, then unselected
        allChips.addAll(selectedChips);
        allChips.addAll(unselectedChips);
    }

    /**
     * Update the selection state in memory
     */
    private void updateChipStatus(String text, boolean isChecked) {
        for (ChipData data : allChips) {
            if (data.text.equals(text)) {
                data.isSelected = isChecked;
                break;
            }
        }
    }

    /**
     * Save selected chips to SharedPreferences
     */
    private void saveSelections() {
        Set<String> selectedChips = new HashSet<>();
        for (ChipData data : allChips) {
            if (data.isSelected) {
                selectedChips.add(data.text);
            }
        }
        sharedPreferences.edit().putStringSet(SELECTED_CHIPS_KEY, selectedChips).apply();
    }

    /**
     * Reorder chips: selected first, then unselected
     */
    private void reorderChips() {
        // Separate into selected and unselected
        List<ChipData> selectedChips = new ArrayList<>();
        List<ChipData> unselectedChips = new ArrayList<>();

        for (ChipData data : allChips) {
            if (data.isSelected) {
                selectedChips.add(data);
            } else {
                unselectedChips.add(data);
            }
        }

        // Update allChips list with new order
        allChips.clear();
        allChips.addAll(selectedChips);
        allChips.addAll(unselectedChips);

        // Rebuild UI
        chipGroup.removeAllViews();

        CompoundButton.OnCheckedChangeListener chipListener = (buttonView, isChecked) -> {
            String chipText = buttonView.getText().toString();
            updateChipStatus(chipText, isChecked);
            saveSelections();
            reorderChips();
        };

        // Add chips in new order
        for (ChipData data : allChips) {
            chipGroup.addView(createChip(data, chipListener));
        }
    }

    /**
     * Create a Chip with the given data and listener
     */
    private Chip createChip(ChipData data, CompoundButton.OnCheckedChangeListener listener) {
        Chip chip = new Chip(this);
        chip.setText(data.text);
        chip.setCheckable(true);
        chip.setChecked(data.isSelected);
        chip.setCheckedIconVisible(false);
        chip.setClickable(true);
        chip.setChipBackgroundColorResource(R.color.chip_background_selector);
        chip.setTextColor(getResources().getColor(android.R.color.white, null));
        chip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

        int paddingH = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
        int paddingV = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        chip.setPadding(paddingH, paddingV, paddingH, paddingV);

        chip.setLayoutParams(new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        chip.setOnCheckedChangeListener(listener);
        return chip;
    }

    /**
     * Helper class to store chip state
     */
    private static class ChipData {
        String text;
        boolean isSelected;

        ChipData(String text, boolean isSelected) {
            this.text = text;
            this.isSelected = isSelected;
        }
    }
}
