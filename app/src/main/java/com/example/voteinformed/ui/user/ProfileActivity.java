package com.example.voteinformed.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voteinformed.R;
import com.example.voteinformed.ui.home.HomescreenActivity;
import com.example.voteinformed.ui.saved.SavedActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Back Button
        ImageButton btnBack = findViewById(R.id.btnBackProfile);
        btnBack.setOnClickListener(v -> finish());

        // Personal Information
        LinearLayout btnPersonalInfo = findViewById(R.id.btnPersonalInfo);
        btnPersonalInfo.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, PersonalInfoActivity.class));
        });

        // Bookmarks (Navigates to SavedActivity)
        LinearLayout btnBookmarks = findViewById(R.id.btnBookmarks);
        btnBookmarks.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, SavedActivity.class));
        });

        // Log Out (Redirect to HomescreenActivity)
        LinearLayout btnLogOut = findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, HomescreenActivity.class));
            finish();
        });
    }
}
