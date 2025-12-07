package com.example.voteinformed.ui.politician;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import com.example.voteinformed.R;
import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.repository.VoteInformed_Repository;
import com.example.voteinformed.ui.home.HomeActivity;
import com.example.voteinformed.ui.home.HomescreenActivity;
import com.example.voteinformed.ui.saved.SavedActivity;
import com.example.voteinformed.ui.search.SearchActivity;
import com.example.voteinformed.ui.user.ProfileActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;


public class PoliticianComparisonActivity extends AppCompatActivity
        implements PoliticianSearchDialog.OnPoliticianSelectedListener {

    private PoliticianComparisonViewModel viewModel;

    private Politician leftPolitician;
    private Politician rightPolitician;

    private boolean selectingLeft = true;

    private DrawerLayout drawerLayout;

    private MaterialButton tabOverview, tabIssues, tabContact;
    private MaterialButton currentActiveTab;
    private ValueAnimator currentColorAnimator;
    private boolean isAnimating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politician_comparison);

        viewModel = new ViewModelProvider(this).get(PoliticianComparisonViewModel.class);

        initDrawerMenu();
        initViews();
        setupUI();
        setupInitialButtonStates();
        observeViewModel();

        // Restore last selected politicians first
        restoreComparisonState();

        // Profile → Compare always overrides LEFT (highest priority)
        int incomingId = getIntent().getIntExtra("politician_id", -1);

        if (incomingId != -1) {
            VoteInformed_Repository repo =
                    new VoteInformed_Repository(getApplicationContext());

            repo.getPoliticianById(incomingId).observe(this, politician -> {
                if (politician != null) {
                    viewModel.setLeftPolitician(politician);
                }
            });
        }

    }

    private void initDrawerMenu() {
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);

        ImageButton btnLeftMenu = findViewById(R.id.btnLeftMenu);
        btnLeftMenu.setOnClickListener(v ->
                drawerLayout.openDrawer(GravityCompat.START)
        );

        setupNavHeader(navView);

        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home)
                startActivity(new Intent(this, HomeActivity.class));
            else if (id == R.id.nav_search)
                startActivity(new Intent(this, SearchActivity.class));
            else if (id == R.id.nav_saved)
                startActivity(new Intent(this, SavedActivity.class));
            else if (id == R.id.nav_profile)
                startActivity(new Intent(this, ProfileActivity.class));
            else if (id == R.id.nav_sign_out) {
                startActivity(new Intent(this, HomescreenActivity.class));
                finish();
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void setupUI() {

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        findViewById(R.id.btnSwapLeft).setOnClickListener(v -> {
            selectingLeft = true;
            openSearchDialog();
        });

        findViewById(R.id.btnSwapRight).setOnClickListener(v -> {
            selectingLeft = false;
            openSearchDialog();
        });

        findViewById(R.id.btnRemoveLeft)
                .setOnClickListener(v -> viewModel.setLeftPolitician(null));

        findViewById(R.id.btnRemoveRight)
                .setOnClickListener(v -> viewModel.setRightPolitician(null));

        ImageView leftImage = findViewById(R.id.imageLeft);
        leftImage.setOnClickListener(v -> openProfile(leftPolitician));

        ImageView rightImage = findViewById(R.id.imageRight);
        rightImage.setOnClickListener(v -> openProfile(rightPolitician));
    }

    private void openSearchDialog() {
        FragmentManager fm = getSupportFragmentManager();
        PoliticianSearchDialog dialog = PoliticianSearchDialog.newInstance();
        dialog.show(fm, "PoliticianSearchDialog");
    }


    @Override
    public void onPoliticianSelected(Politician politician) {
        if (politician == null) return;

        if (selectingLeft) {
            viewModel.setLeftPolitician(politician);
        } else {
            viewModel.setRightPolitician(politician);
        }
    }

    private void observeViewModel() {

        viewModel.getLeftPolitician().observe(this, politician -> {
            leftPolitician = politician;
            updateCard(true, politician);
        });

        viewModel.getRightPolitician().observe(this, politician -> {
            rightPolitician = politician;
            updateCard(false, politician);
        });
    }

    private void updateCard(boolean isLeft, Politician politician) {
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
            clearAllRows(side);
            return;
        }

        tvName.setText(politician.getPolitician_name());
        tvParty.setText(politician.getPolitician_party());

        String url = politician.getPolitician_image_url();
        if (url != null && !url.isEmpty()) {
            Glide.with(this).load(url).into(ivImage);
        } else {
            ivImage.setImageResource(R.drawable.user);
        }
        // Show metrics based on active tab
        if (currentActiveTab == tabOverview) {

            setMetricRow(side, 1, "Office Location", politician.getPolitician_location());
            setMetricRow(side, 2, "Party", politician.getPolitician_party());
            setMetricRow(side, 3, "Background", politician.getPolitician_background());

            for (int i = 4; i <= 7; i++) setMetricRow(side, i, null, null);

        } else if (currentActiveTab == tabIssues) {

            setMetricRow(side, 1, "Political Background", politician.getPolitician_background());
            setMetricRow(side, 2, "Key Focus", "Issue data not available");
            setMetricRow(side, 3, "Policy Direction", "Issue data not available");

            for (int i = 4; i <= 7; i++) setMetricRow(side, i, null, null);

        } else if (currentActiveTab == tabContact) {

            setMetricRow(side, 1, "Contact", politician.getPolitician_contact());
            setMetricRow(side, 2, "Office Location", politician.getPolitician_location());

            for (int i = 3; i <= 7; i++) setMetricRow(side, i, null, null);
        }

    }

    private void openProfile(Politician politician) {
        if (politician == null) return;

        Intent intent = new Intent(this, PoliticianProfileActivity.class);
        intent.putExtra("politician_id", politician.getPolitician_id());
        startActivity(intent);
    }

    private void initViews() {
        tabOverview = findViewById(R.id.tabOverview);
        tabIssues = findViewById(R.id.tabIssues);
        tabContact = findViewById(R.id.tabContact);
        currentActiveTab = tabOverview;

        View.OnClickListener tabClick = v -> {
            MaterialButton clicked = (MaterialButton) v;
            switchTab(clicked);
        };

        tabOverview.setOnClickListener(tabClick);
        tabIssues.setOnClickListener(tabClick);
        tabContact.setOnClickListener(tabClick);
    }

    private void setupInitialButtonStates() {
        int blue = ContextCompat.getColor(this, R.color.app_primary_blue);
        int gray = Color.parseColor("#F5F5F5");
        int white = ContextCompat.getColor(this, android.R.color.white);
        int dark = ContextCompat.getColor(this, R.color.text_primary);

        tabOverview.setBackgroundColor(blue);
        tabOverview.setTextColor(dark);
        tabIssues.setBackgroundColor(gray);
        tabIssues.setTextColor(dark);
        tabContact.setBackgroundColor(gray);
        tabContact.setTextColor(dark);
    }

    private void switchTab(MaterialButton clicked) {
        if (currentActiveTab == clicked || isAnimating) return;
        isAnimating = true;

        animateButtonColors(currentActiveTab, clicked);
        currentActiveTab = clicked;

        if (leftPolitician != null) updateCard(true, leftPolitician);
        if (rightPolitician != null) updateCard(false, rightPolitician);

    }

    private void animateButtonColors(MaterialButton from, MaterialButton to) {
        int blue = ContextCompat.getColor(this, R.color.app_primary_blue);
        int gray = Color.parseColor("#F5F5F5");
        int white = ContextCompat.getColor(this, android.R.color.white);
        int dark = ContextCompat.getColor(this, R.color.text_primary);

        currentColorAnimator = ValueAnimator.ofArgb(blue, gray);
        currentColorAnimator.setDuration(250);
        currentColorAnimator.addUpdateListener(a ->
                from.setBackgroundColor((int) a.getAnimatedValue()));
        currentColorAnimator.start();

        ValueAnimator toAnim = ValueAnimator.ofArgb(gray, blue);
        toAnim.setDuration(250);
        toAnim.addUpdateListener(a ->
                to.setBackgroundColor((int) a.getAnimatedValue()));
        toAnim.start();

        isAnimating = false;
    }

    private void setupNavHeader(NavigationView navView) {
        if (navView.getHeaderCount() > 0) {
            View header = navView.getHeaderView(0);
            TextView username = header.findViewById(R.id.user_name);
            TextView email = header.findViewById(R.id.user_email);

            SharedPreferences prefs = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
            int userId = prefs.getInt("user_id", -1);

            if (userId != -1) {
                VoteInformed_Repository repo =
                        new VoteInformed_Repository(getApplicationContext());

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

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences prefs = getSharedPreferences("comparison", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Save LEFT politician
        if (leftPolitician != null) {
            editor.putInt("left_id", leftPolitician.getPolitician_id());
        }

        // Save RIGHT politician
        if (rightPolitician != null) {
            editor.putInt("right_id", rightPolitician.getPolitician_id());
        }
        editor.apply();
    }

    // Restore last selected politicians
    private void restoreComparisonState() {

        SharedPreferences prefs = getSharedPreferences("comparison", MODE_PRIVATE);

        int savedLeftId = prefs.getInt("left_id", -1);
        int savedRightId = prefs.getInt("right_id", -1);

        VoteInformed_Repository repo =
                new VoteInformed_Repository(getApplicationContext());

        // Restore LEFT
        if (savedLeftId != -1) {
            repo.getPoliticianById(savedLeftId).observe(this, politician -> {
                if (politician != null) {
                    viewModel.setLeftPolitician(politician);
                }
            });
        }

        // Restore RIGHT
        if (savedRightId != -1) {
            repo.getPoliticianById(savedRightId).observe(this, politician -> {
                if (politician != null) {
                    viewModel.setRightPolitician(politician);
                }
            });
        }
    }

    // Set title and value for a metric row
    private void setMetricRow(String side, int index, String label, String value) {
        int layoutId = getResources().getIdentifier(
                "metric" + side + index,
                "id",
                getPackageName()
        );

        View row = findViewById(layoutId);

        if (row == null) return;

        if (value == null || value.trim().isEmpty()) {
            row.setVisibility(View.GONE); // Hide empty data
        } else {
            row.setVisibility(View.VISIBLE);
            TextView tvLabel = row.findViewById(R.id.metricName);
            TextView tvValue = row.findViewById(R.id.metricContent);

            if (tvLabel != null) tvLabel.setText(label);
            if (tvValue != null) tvValue.setText(value);
        }
    }

    // Clear all metric rows for a side
    private void clearAllRows(String side) {
        for (int i = 1; i <= 7; i++) {
            setMetricRow(side, i, null, null);
        }
    }



}
