package com.example.voteinformed.ui.politician;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import com.example.voteinformed.R;
import com.example.voteinformed.data.entity.Politician;
import java.util.ArrayList;
import java.util.List;

public class PoliticianComparisonActivity extends AppCompatActivity {

    private PoliticianComparisonViewModel viewModel;
    private List<Politician> allPoliticians = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politician_comparison);

        // Initialize the ViewModel
        viewModel = new ViewModelProvider(this).get(PoliticianComparisonViewModel.class);

        setupUI();
        observeViewModel();
    }

    private void setupUI() {
        // back button
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // swap buttons
        findViewById(R.id.btnSwapLeft).setOnClickListener(v -> showSelectionDialog(true));
        findViewById(R.id.btnSwapRight).setOnClickListener(v -> showSelectionDialog(false));

        // remove buttons
        findViewById(R.id.btnRemoveLeft).setOnClickListener(v -> viewModel.setLeftPolitician(null));
        findViewById(R.id.btnRemoveRight).setOnClickListener(v -> viewModel.setRightPolitician(null));
    }

    private void observeViewModel() {
        // get list of all politicians
        viewModel.getAllPoliticians().observe(this, politicians -> {
            if (politicians != null && !politicians.isEmpty()) {
                allPoliticians.clear();
                allPoliticians.addAll(politicians);
                viewModel.setInitialPoliticians(politicians);
            } else {
                Toast.makeText(this, "No politicians found.", Toast.LENGTH_SHORT).show();
            }
        });

        // getLeftPolitictian and update
        viewModel.getLeftPolitician().observe(this, politician -> {
            updateCard(true, politician);
        });

        // getRightPolitictian and update
        viewModel.getRightPolitician().observe(this, politician -> {
            updateCard(false, politician);
        });
    }

    private void showSelectionDialog(boolean isLeft) {
        if (allPoliticians.isEmpty()) {
            Toast.makeText(this, "No politicians to select.", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] names = new String[allPoliticians.size()];
        for (int i = 0; i < allPoliticians.size(); i++) {
            names[i] = allPoliticians.get(i).getPolitician_name();
        }

        new AlertDialog.Builder(this)
                .setTitle("Select Candidate")
                .setItems(names, (dialog, which) -> {
                    Politician selected = allPoliticians.get(which);
                    if (isLeft) {
                        viewModel.setLeftPolitician(selected);
                    } else {
                        viewModel.setRightPolitician(selected);
                    }
                })
                .show();
    }

    // Updates UI of card
    private void updateCard(boolean isLeft, Politician politician) {
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

            for (int i = 1; i <= 7; i++) setMetricRow(side, i, null, null);
            return;
        }

        tvName.setText(politician.getPolitician_name());
        tvParty.setText(politician.getPolitician_party());

        String url = politician.getPolitician_image_url();
        if (url != null && !url.equals("default_image") && !url.isEmpty()) {
            Glide.with(this).load(url).placeholder(R.drawable.user).into(ivImage);
        } else {
            ivImage.setImageResource(R.drawable.user);
        }

        setMetricRow(side, 1, "Office Location", politician.getPolitician_location());
        setMetricRow(side, 2, "Contact Info", politician.getPolitician_contact());
        setMetricRow(side, 3, "Biography", politician.getPolitician_background());

        for (int i = 4; i <= 7; i++) setMetricRow(side, i, null, null);
    }

    // Set title and text for a specific row
    private void setMetricRow(String side, int index, String label, String content) {
        int layoutId = getResources().getIdentifier("metric" + side + index, "id", getPackageName());
        View row = findViewById(layoutId);

        if (row != null) {
            if (content == null || content.isEmpty()) {
                row.setVisibility(View.GONE);
            } else {
                row.setVisibility(View.VISIBLE);
                TextView tvLabel = row.findViewById(R.id.metricName);
                TextView tvContent = row.findViewById(R.id.metricContent);
                tvLabel.setText(label);
                tvContent.setText(content);
            }
        }
    }
}
