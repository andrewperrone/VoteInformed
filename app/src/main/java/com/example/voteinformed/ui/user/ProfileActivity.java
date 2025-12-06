package com.example.voteinformed.ui.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voteinformed.R;
import com.example.voteinformed.data.repository.VoteInformed_Repository;
import com.example.voteinformed.ui.home.HomescreenActivity;
import com.example.voteinformed.ui.saved.SavedActivity;

public class ProfileActivity extends AppCompatActivity {

    private VoteInformed_Repository repository;
    private TextView profileName, profileEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        repository = new VoteInformed_Repository(getApplicationContext());

        // Initialize Views   
        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);

        // Load User Data   
        loadUserData();

        // Back Button   
        ImageButton btnBack = findViewById(R.id.btnBackProfile);
        btnBack.setOnClickListener(v -> finish());

        // Personal Information   
        LinearLayout btnPersonalInfo = findViewById(R.id.btnPersonalInfo);
        btnPersonalInfo.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, PersonalInfoActivity.class));
        });

        // Bookmarks   
        LinearLayout btnBookmarks = findViewById(R.id.btnBookmarks);
        btnBookmarks.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, SavedActivity.class));
        });

        // Log Out   
        LinearLayout btnLogOut = findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(v -> {
            // Clear Session
            SharedPreferences prefs = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
            prefs.edit().clear().apply();

            startActivity(new Intent(ProfileActivity.this, HomescreenActivity.class));
            finish();
        });
    }

    private void loadUserData() {
        SharedPreferences prefs = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        int userId = prefs.getInt("user_id", -1);

        if (userId != -1) {
            // Observe LiveData: This automatically updates the UI if the DB changes!
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