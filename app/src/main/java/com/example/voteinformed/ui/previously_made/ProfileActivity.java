package com.example.voteinformed.ui.previously_made;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voteinformed.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // -------------------------
        // Back Button
        // -------------------------
        ImageButton btnBack = findViewById(R.id.btnBackProfile);
        btnBack.setOnClickListener(v -> finish());

        // -------------------------
        // Profile Settings Options
        // -------------------------

    /*
        // Language settings
        LinearLayout btnLanguage = findViewById(R.id.btnLanguage);
        btnLanguage.setOnClickListener(v -> {
            Toast.makeText(this, "Language settings", Toast.LENGTH_SHORT).show();
            // TODO: Implement language settings screen
        });
    */

        // Personal Information
        LinearLayout btnPersonalInfo = findViewById(R.id.btnPersonalInfo);
        btnPersonalInfo.setOnClickListener(v -> {
            Toast.makeText(this, "Personal Information", Toast.LENGTH_SHORT).show();
            // TODO: Implement personal info screen
        });

        // Login Security
        LinearLayout btnLoginSecurity = findViewById(R.id.btnLoginSecurity);
        btnLoginSecurity.setOnClickListener(v -> {
            Toast.makeText(this, "Login Security", Toast.LENGTH_SHORT).show();
            // TODO: Implement login security settings
        });

        // Bookmarks (Navigates to SavedActivity)
        LinearLayout btnBookmarks = findViewById(R.id.btnBookmarks);
        btnBookmarks.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, SavedActivity.class));
        });

        // Following
        LinearLayout btnFollowing = findViewById(R.id.btnFollowing);
        btnFollowing.setOnClickListener(v -> {
            Toast.makeText(this, "Following", Toast.LENGTH_SHORT).show();
            // TODO: Implement following screen
        });

        // Search History
        LinearLayout btnSearchHistory = findViewById(R.id.btnSearchHistory);
        btnSearchHistory.setOnClickListener(v -> {
            Toast.makeText(this, "Search History", Toast.LENGTH_SHORT).show();
            // TODO: Implement search history screen
        });

        // Clear Cache
        LinearLayout btnClearCache = findViewById(R.id.btnClearCache);
        btnClearCache.setOnClickListener(v -> {
            Toast.makeText(this, "Cache cleared", Toast.LENGTH_SHORT).show();
            // TODO: Implement cache clearing logic
        });

        // Clear History
        LinearLayout btnClearHistory = findViewById(R.id.btnClearHistory);
        btnClearHistory.setOnClickListener(v -> {
            Toast.makeText(this, "History cleared", Toast.LENGTH_SHORT).show();
            // TODO: Implement history clearing logic
        });

        // Log Out (Redirect to HomescreenActivity)
        LinearLayout btnLogOut = findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, HomescreenActivity.class));
            finish();
        });
    }
}
