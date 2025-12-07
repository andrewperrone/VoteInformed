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

import androidx.appcompat.app.AppCompatActivity;
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
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class PoliticianComparisonActivity extends AppCompatActivity{
        //implements PoliticianSearchDialog.OnPoliticianSelectedListener

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

        viewModel = new ViewModelProvider(this).get(PoliticianComparisonViewModel.class);

        initDrawerMenu();
        setupUI();
        observeViewModel();

        int incomingId = getIntent().getIntExtra("politician_id", -1);
        if (incomingId != -1) {
            viewModel.setInitialById(incomingId);
        }

        restoreComparisonState();
    }

    private void initDrawerMenu() {
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        btnLeftMenu = findViewById(R.id.btnLeftMenu);

        if (drawerLayout == null || navView == null || btnLeftMenu == null) return;

        btnLeftMenu.setOnClickListener(v ->
                drawerLayout.openDrawer(GravityCompat.START)
        );

        setupNavHeader(navView);

        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                startActivity(new Intent(this, HomeActivity.class));
            } else if (id == R.id.nav_search) {
                startActivity(new Intent(this, SearchActivity.class));
            } else if (id == R.id.nav_saved) {
                startActivity(new Intent(this, SavedActivity.class));
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
            } else if (id == R.id.nav_sign_out) {
                startActivity(new Intent(this, HomescreenActivity.class));
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


        findViewById(R.id.btnBack).setOnClickListener(v -> finish());


        /*findViewById(R.id.btnSwapLeft).setOnClickListener(v -> openSearchDialog(true));
        findViewById(R.id.btnSwapRight).setOnClickListener(v -> openSearchDialog(false));*/

        findViewById(R.id.btnRemoveLeft).setOnClickListener(v -> viewModel.setLeftPolitician(null));
        findViewById(R.id.btnRemoveRight).setOnClickListener(v -> viewModel.setRightPolitician(null));

        // OPEN PROFILE THROUGH PROFILE PHOTO
        ImageView leftImage = findViewById(R.id.imageLeft);
        if (leftImage != null) leftImage.setOnClickListener(v -> openProfile(leftPolitician));

        ImageView rightImage = findViewById(R.id.imageRight);
        if (rightImage != null) rightImage.setOnClickListener(v -> openProfile(rightPolitician));

    }

    /*private void openSearchDialog(boolean isLeft) {
        FragmentManager fm = getSupportFragmentManager();
        PoliticianSearchDialog dialog = PoliticianSearchDialog.newInstance(isLeft);
        dialog.show(fm, "PoliticianSearchDialog");
    }*/

    /*@Override
    public void onPoliticianSelected(Politician politician, boolean isLeft) {
        if (isLeft) viewModel.setLeftPolitician(politician);
        else viewModel.setRightPolitician(politician);
    }*/

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

        TextView tvName = findViewById(getResources()
                .getIdentifier("name" + side, "id", getPackageName()));
        TextView tvParty = findViewById(getResources()
                .getIdentifier("party" + side, "id", getPackageName()));
        ImageView ivImage = findViewById(getResources()
                .getIdentifier("image" + side, "id", getPackageName()));

        if (politician == null) {
            tvName.setText("Empty Slot");
            tvParty.setText("Select a candidate");
            ivImage.setImageResource(R.drawable.user);
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
    }

    private void openProfile(Politician politician) {
        if (politician == null) return;

        Intent intent = new Intent(this, PoliticianProfileActivity.class);
        intent.putExtra("politician_id", politician.getPolitician_id());
        startActivity(intent);
    }


    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences prefs = getSharedPreferences("comparison", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        if (leftPolitician != null)
            editor.putInt("left_id", leftPolitician.getPolitician_id());

        if (rightPolitician != null)
            editor.putInt("right_id", rightPolitician.getPolitician_id());

        editor.apply();
    }

    private void restoreComparisonState() {
        SharedPreferences prefs = getSharedPreferences("comparison", MODE_PRIVATE);

        int leftId = prefs.getInt("left_id", -1);
        int rightId = prefs.getInt("right_id", -1);

        if (leftId != -1) viewModel.setInitialById(leftId);
        if (rightId != -1) viewModel.setRightById(rightId);
    }
}
