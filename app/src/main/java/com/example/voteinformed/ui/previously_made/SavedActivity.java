package com.example.voteinformed.ui.previously_made;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.voteinformed.R;
import com.google.android.material.navigation.NavigationView;

public class SavedActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        // Initialize drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);

        // Highlight current item and make it non-clickable
        navView.setCheckedItem(R.id.nav_saved);
        navView.getMenu().findItem(R.id.nav_saved).setEnabled(false);

        // Setup navigation header
        setupNavHeader(navView);

        // Left menu button: opens drawer
        ImageButton btnLeft = findViewById(R.id.btnLeftMenu);
        btnLeft.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        // Profile button: opens ProfileActivity
        ImageButton btnRight = findViewById(R.id.btnRightMenu);
        btnRight.setOnClickListener(v -> {
            Intent intent = new Intent(SavedActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        // Setup navigation menu
        setupNavMenu(navView);
    }

    private void setupNavHeader(NavigationView navView) {
        if (navView.getHeaderCount() > 0) {
            View headerView = navView.getHeaderView(0);
            ImageView profileImage = headerView.findViewById(R.id.profile_image);
            TextView userName = headerView.findViewById(R.id.user_name);
            TextView userEmail = headerView.findViewById(R.id.user_email);

            userName.setText("John Doe");
            userEmail.setText("john.doe@example.com");
        }
    }

    private void setupNavMenu(NavigationView navView) {
        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                startActivity(new Intent(SavedActivity.this, HomeActivity.class));
            } else if (id == R.id.nav_search) {
                startActivity(new Intent(SavedActivity.this, SearchActivity.class));
            } else if (id == R.id.nav_saved) {
                // Already on Saved page
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            } else if (id == R.id.nav_comparison) {
                startActivity(new Intent(SavedActivity.this, PoliticianComparisonActivity.class));
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(SavedActivity.this, ProfileActivity.class));
            } else if (id == R.id.nav_sign_out) {
                startActivity(new Intent(SavedActivity.this, HomescreenActivity.class));
                finish();
            } else {
                return false;
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}