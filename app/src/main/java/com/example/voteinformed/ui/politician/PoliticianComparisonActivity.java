package com.example.voteinformed.ui.politician;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.bumptech.glide.Glide;
import com.example.voteinformed.R;
import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.repository.VoteInformed_Repository;
import com.example.voteinformed.ui.home.HomeActivity;
import com.example.voteinformed.ui.saved.SavedActivity;
import com.example.voteinformed.ui.search.SearchActivity;
import com.example.voteinformed.ui.user.ProfileActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class PoliticianComparisonActivity extends AppCompatActivity {

    private PoliticianComparisonViewModel viewModel;
    private List<Politician> allPoliticians = new ArrayList<>();

    private Politician leftPolitician;
    private Politician rightPolitician;

    private DrawerLayout drawerLayout;
    private ImageButton btnLeftMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politician_comparison);

        // Initialize the ViewModel
        viewModel = new ViewModelProvider(this).get(PoliticianComparisonViewModel.class);

        setupUI();
        observeViewModel();
    }

    private void initDrawerMenu() {
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);

        btnLeftMenu = findViewById(R.id.btnLeftMenu);
        btnLeftMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        setupNavHeader(navView);

        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                startActivity(new Intent(this, HomeActivity.class));
            } else if (id == R.id.nav_search) {
                startActivity(new Intent(this, SearchActivity.class));
            } else if (id == R.id.nav_saved) {
                startActivity(new Intent(this, SavedActivity.class));
            } else if (id == R.id.nav_comparison) {
                // 현재 화면 → 아무것도 안함
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
            } else if (id == R.id.nav_sign_out) {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void setupNavHeader(NavigationView navView) {
        if (navView.getHeaderCount() > 0) {
            View header = navView.getHeaderView(0);
            TextView username = header.findViewById(R.id.user_name);
            TextView email = header.findViewById(R.id.user_email);

            SharedPreferences prefs = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
            int userId = prefs.getInt("user_id", -1);

            if (userId != -1) {
                VoteInformed_Repository repo = new VoteInformed_Repository(getApplicationContext());
                repo.getUserById(userId).observe(this, user -> {
                    if (user != null) {
                        username.setText(user.getName());
                        email.setText(user.getEmail());
                    }
                });
            } else {
                username.setText("Guest");
                email.setText("Please log in");
            }
        }
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

        setMetricRow(side, 1, "Office Location", politician.getPolitician_location());
        setMetricRow(side, 2, "Contact Info", politician.getPolitician_contact());
        setMetricRow(side, 3, "Biography", politician.getPolitician_background());

        for (int i = 4; i <= 7; i++) setMetricRow(side, i, null, null);
    }

    // Set title and text for a specific row
    private void setMetricRow(String side, int index, String label, String content) {
        // Find the included layout (ex: metricLeft1)
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