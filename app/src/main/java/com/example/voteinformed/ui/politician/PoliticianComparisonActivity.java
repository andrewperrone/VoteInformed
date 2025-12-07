package com.example.voteinformed.ui.politician;

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

    private VoteInformed_Repository repository;
    private List<Politician> allPoliticians = new ArrayList<>();

    private Politician leftPolitician;
    private Politician rightPolitician;

    private DrawerLayout drawerLayout;
    private ImageButton btnLeftMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politician_comparison);

        repository = new VoteInformed_Repository(getApplicationContext());

        initDrawerMenu();
        initButtons();

        loadPoliticians();
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

    private void initButtons() {
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        findViewById(R.id.btnSwapLeft).setOnClickListener(v -> showSelectionDialog(true));
        findViewById(R.id.btnSwapRight).setOnClickListener(v -> showSelectionDialog(false));

        findViewById(R.id.btnRemoveLeft).setOnClickListener(v -> updateCard(true, null));
        findViewById(R.id.btnRemoveRight).setOnClickListener(v -> updateCard(false, null));
    }

    private void loadPoliticians() {
        repository.getAllPoliticians().observe(this, politicians -> {
            if (politicians != null && !politicians.isEmpty()) {
                this.allPoliticians = politicians;

                if (leftPolitician == null) updateCard(true, politicians.get(0));
                if (rightPolitician == null && politicians.size() > 1)
                    updateCard(false, politicians.get(1));
            } else {
                Toast.makeText(this, "No politicians found.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showSelectionDialog(boolean isLeft) {
        if (allPoliticians.isEmpty()) return;

        String[] names = new String[allPoliticians.size()];
        for (int i = 0; i < allPoliticians.size(); i++)
            names[i] = allPoliticians.get(i).getPolitician_name();

        new AlertDialog.Builder(this)
                .setTitle("Select Candidate")
                .setItems(names, (dialog, which) -> updateCard(isLeft, allPoliticians.get(which)))
                .show();
    }

    private void updateCard(boolean isLeft, Politician politician) {
        if (isLeft) leftPolitician = politician;
        else rightPolitician = politician;

        String side = isLeft ? "Left" : "Right";

        int nameId = getResources().getIdentifier("name" + side, "id", getPackageName());
        int partyId = getResources().getIdentifier("party" + side, "id", getPackageName());
        int imageId = getResources().getIdentifier("image" + side, "id", getPackageName());

        TextView tvName = findViewById(nameId);
        TextView tvParty = findViewById(partyId);
        ImageView ivImage = findViewById(imageId);

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
        if (url != null && !url.isEmpty() && !url.equals("default_image"))
            Glide.with(this).load(url).placeholder(R.drawable.user).into(ivImage);
        else
            ivImage.setImageResource(R.drawable.user);

        setMetricRow(side, 1, "Office Location", politician.getPolitician_location());
        setMetricRow(side, 2, "Contact Info", politician.getPolitician_contact());
        setMetricRow(side, 3, "Biography", politician.getPolitician_background());

        for (int i = 4; i <= 7; i++) setMetricRow(side, i, null, null);
    }

    private void setMetricRow(String side, int index, String label, String content) {
        int layoutId = getResources().getIdentifier("metric" + side + index, "id", getPackageName());
        View row = findViewById(layoutId);

        if (row == null) return;

        if (content == null || content.isEmpty()) {
            row.setVisibility(View.GONE);
        } else {
            row.setVisibility(View.VISIBLE);
            ((TextView) row.findViewById(R.id.metricName)).setText(label);
            ((TextView) row.findViewById(R.id.metricContent)).setText(content);
        }
    }
}
