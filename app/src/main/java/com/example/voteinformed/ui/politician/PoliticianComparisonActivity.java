package com.example.voteinformed.ui.politician;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.voteinformed.R;
import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.repository.VoteInformed_Repository;

import java.util.ArrayList;
import java.util.List;

public class PoliticianComparisonActivity extends AppCompatActivity {

    private VoteInformed_Repository repository;
    private List<Politician> allPoliticians = new ArrayList<>();

    // Keep track of who is on which side
    private Politician leftPolitician;
    private Politician rightPolitician;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politician_comparison);

        repository = new VoteInformed_Repository(getApplicationContext());

        // Back Buttone
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // Swap Buttons
        findViewById(R.id.btnSwapLeft).setOnClickListener(v -> showSelectionDialog(true));
        findViewById(R.id.btnSwapRight).setOnClickListener(v -> showSelectionDialog(false));

        // Remove Buttons
        findViewById(R.id.btnRemoveLeft).setOnClickListener(v -> updateCard(true, null));
        findViewById(R.id.btnRemoveRight).setOnClickListener(v -> updateCard(false, null));

        // Load politicians
        loadPoliticians();
    }

    private void loadPoliticians() {
        repository.getAllPoliticians().observe(this, politicians -> {
            if (politicians != null && !politicians.isEmpty()) {
                this.allPoliticians = politicians;

                // Load defaults if slots are empty
                if (leftPolitician == null) updateCard(true, politicians.get(0));
                if (rightPolitician == null && politicians.size() > 1) updateCard(false, politicians.get(1));
            } else {
                Toast.makeText(this, "No politicians found.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showSelectionDialog(boolean isLeft) {
        if (allPoliticians.isEmpty()) return;

        String[] names = new String[allPoliticians.size()];
        for (int i = 0; i < allPoliticians.size(); i++) {
            names[i] = allPoliticians.get(i).getPolitician_name();
        }

        new AlertDialog.Builder(this)
                .setTitle("Select Candidate")
                .setItems(names, (dialog, which) -> {
                    updateCard(isLeft, allPoliticians.get(which));
                })
                .show();
    }

    // Updates UI for a card
    private void updateCard(boolean isLeft, Politician politician) {
        if (isLeft) leftPolitician = politician;
        else rightPolitician = politician;

        String side = isLeft ? "Left" : "Right";

        // Find the main views for this side
        int nameId = getResources().getIdentifier("name" + side, "id", getPackageName());
        int partyId = getResources().getIdentifier("party" + side, "id", getPackageName());
        int imageId = getResources().getIdentifier("image" + side, "id", getPackageName());

        TextView tvName = findViewById(nameId);
        TextView tvParty = findViewById(partyId);
        ImageView ivImage = findViewById(imageId);

        // Handle empty state
        if (politician == null) {
            tvName.setText("Empty Slot");
            tvParty.setText("Select a candidate");
            ivImage.setImageResource(R.drawable.user);

            // Hide all info rows
            for (int i = 1; i <= 7; i++) setMetricRow(side, i, null, null);
            return;
        }

        // Set Header Info
        tvName.setText(politician.getPolitician_name());
        tvParty.setText(politician.getPolitician_party());

        // Set Image
        String url = politician.getPolitician_image_url();
        if (url != null && !url.equals("default_image") && !url.isEmpty()) {
            Glide.with(this).load(url).placeholder(R.drawable.user).into(ivImage);
        } else {
            ivImage.setImageResource(R.drawable.user);
        }

        // Set The Data Rows
        setMetricRow(side, 1, "Office Location", politician.getPolitician_location());
        setMetricRow(side, 2, "Contact Info", politician.getPolitician_contact());
        setMetricRow(side, 3, "Biography", politician.getPolitician_background());

        // Clear unused rows (4-7) so they don't take up space
        setMetricRow(side, 4, null, null);
        setMetricRow(side, 5, null, null);
        setMetricRow(side, 6, null, null);
        setMetricRow(side, 7, null, null);
    }

    // Set title and text for a specific row
    private void setMetricRow(String side, int index, String label, String content) {
        // Find the included layout (ex: metricLeft1)
        int layoutId = getResources().getIdentifier("metric" + side + index, "id", getPackageName());
        View row = findViewById(layoutId);

        if (row != null) {
            if (content == null || content.isEmpty()) {
                row.setVisibility(View.GONE); // Hide empty rows
            } else {
                row.setVisibility(View.VISIBLE);

                TextView tvLabel = row.findViewById(R.id.metricName);
                TextView tvContent = row.findViewById(R.id.metricContent); // New ID we made in XML

                tvLabel.setText(label);
                tvContent.setText(content);
            }
        }
    }
}