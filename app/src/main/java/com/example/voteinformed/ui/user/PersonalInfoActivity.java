package com.example.voteinformed.ui.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voteinformed.R;
import com.example.voteinformed.data.entity.User;
import com.example.voteinformed.data.repository.VoteInformed_Repository;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class PersonalInfoActivity extends AppCompatActivity {

    private TextInputEditText inputUsername, inputEmail, inputPollSite;

    private VoteInformed_Repository repository;
    private User currentUser;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        // Initialize repository
        repository = new VoteInformed_Repository(getApplicationContext());

        //Initialize Views
        ImageButton btnBack = findViewById(R.id.btnBackPersonalInfo);
        MaterialButton btnSave = findViewById(R.id.btnSaveChanges);

        inputUsername = findViewById(R.id.inputUsername);
        inputEmail = findViewById(R.id.inputEmail);
        inputPollSite = findViewById(R.id.inputPollSite);

        // Get User ID and Load Data,
        // USER ID MUST BE SAVED IN SHARED PREFERENCES DURING LOGIN
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt("user_id", -1);

        if (userId != -1) {
            loadUserData();
        } else {
            Toast.makeText(this, "Error: No user logged in", Toast.LENGTH_SHORT).show();
        }

        // Back Button Logic
        btnBack.setOnClickListener(v -> finish());

        // Save Button Logic
        btnSave.setOnClickListener(v -> {
            String name = inputUsername.getText().toString().trim();
            String email = inputEmail.getText().toString().trim();
            String pollSite = inputPollSite.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Name and Email are required", Toast.LENGTH_SHORT).show();
            } else {
                // Update the user object with the new data
                if (currentUser != null) {
                    currentUser.setName(name);
                    currentUser.setEmail(email);
                    currentUser.setLocation(pollSite);

                    // Save to database using the callback
                    repository.updateUser(currentUser, success -> {
                        if (success) {
                            Toast.makeText(this, "Information Updated", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(this, "Error: User data not loaded yet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Helper method to fetch user data from DB
    private void loadUserData() {
        repository.getUserById(userId).observe(this, user -> {
            if (user != null) {
                this.currentUser = user; // This initializes the 'currentUser' variable!

                // Pre-fill the text boxes
                if (user.getName() != null) inputUsername.setText(user.getName());
                if (user.getEmail() != null) inputEmail.setText(user.getEmail());
                if (user.getLocation() != null) inputPollSite.setText(user.getLocation());
            }
        });
    }
}