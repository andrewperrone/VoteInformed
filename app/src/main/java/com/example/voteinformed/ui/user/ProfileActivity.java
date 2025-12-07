package com.example.voteinformed.ui.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.voteinformed.R;
import com.example.voteinformed.data.repository.VoteInformed_Repository;
import com.example.voteinformed.ui.elections.ElectionsActivity;
import com.example.voteinformed.ui.home.HomescreenActivity;
import com.example.voteinformed.ui.home.HomeActivity;
import com.example.voteinformed.ui.saved.SavedActivity;
import com.google.android.material.navigation.NavigationView;

public class ProfileActivity extends AppCompatActivity {

    private VoteInformed_Repository repository;
    private TextView profileName, profileEmail;
    private DrawerLayout drawerLayout;
    private ImageButton btnLeftMenu, btnBack;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        repository = new VoteInformed_Repository(getApplicationContext());

        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);

        navView.setCheckedItem(R.id.nav_profile);

        btnBack = findViewById(R.id.btnBackProfile);
        btnBack.setOnClickListener(v -> finish());

        btnLeftMenu = findViewById(R.id.btnLeftMenu);
        btnLeftMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        setupBackPressHandler();

        setupNavMenu();

        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);

        loadUserData();

        setupButtons();
    }

    private void setupButtons() {
        LinearLayout btnPersonalInfo = findViewById(R.id.btnPersonalInfo);
        btnPersonalInfo.setOnClickListener(v ->
                startActivity(new Intent(ProfileActivity.this, PersonalInfoActivity.class))
        );

        LinearLayout btnBookmarks = findViewById(R.id.btnBookmarks);
        btnBookmarks.setOnClickListener(v ->
                startActivity(new Intent(ProfileActivity.this, SavedActivity.class))
        );

        LinearLayout btnLogOut = findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
            prefs.edit().clear().apply();

            startActivity(new Intent(ProfileActivity.this, HomescreenActivity.class));
            finish();
        });
    }

    private void setupNavMenu() {
        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_elections_home){
                startActivity(new Intent(this, ElectionsActivity.class));
            }
            else if (id == R.id.nav_home) {
                startActivity(new Intent(this, HomeActivity.class));
            } else if (id == R.id.nav_search) {
                startActivity(new Intent(this, com.example.voteinformed.ui.search.SearchActivity.class));
            } else if (id == R.id.nav_saved) {
                startActivity(new Intent(this, SavedActivity.class));
            } else if (id == R.id.nav_comparison) {
                startActivity(new Intent(this, com.example.voteinformed.ui.politician.PoliticianComparisonActivity.class));
            } else if (id == R.id.nav_profile) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            } else if (id == R.id.nav_sign_out) {
                SharedPreferences prefs = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
                prefs.edit().clear().apply();
                startActivity(new Intent(this, HomescreenActivity.class));
                finish();
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void setupBackPressHandler() {
        OnBackPressedCallback callback = new OnBackPressedCallback(false) {
            @Override
            public void handleOnBackPressed() {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        };

        getOnBackPressedDispatcher().addCallback(this, callback);

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                callback.setEnabled(true);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                callback.setEnabled(false);
            }
        });
    }

    private void loadUserData() {
        SharedPreferences prefs = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        int userId = prefs.getInt("user_id", -1);

        if (userId != -1) {
            repository.getUserById(userId).observe(this, user -> {
                if (user != null) {
                    profileName.setText(user.getName());
                    profileEmail.setText(user.getEmail());
                }
            });
        } else {
            Toast.makeText(this, "Error: User not logged in", Toast.LENGTH_SHORT).show();
        }
    }
}
