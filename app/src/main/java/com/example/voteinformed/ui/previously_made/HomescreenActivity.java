package com.example.voteinformed.ui.previously_made;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.voteinformed.R;
import com.google.android.material.button.MaterialButton;
import androidx.activity.EdgeToEdge;

public class HomescreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge layout for modern Android styling
        EdgeToEdge.enable(this);

        // Set the content view to the homescreen layout
        setContentView(R.layout.homescreen);

        // Adjust padding to account for system bars (status/navigation bars)
        View root = findViewById(android.R.id.content);
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets sysInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(sysInsets.left, sysInsets.top, sysInsets.right, sysInsets.bottom);
            return insets;
        });

        // Initialize buttons
        MaterialButton loginButton = findViewById(R.id.btnLogin);
        MaterialButton signupButton = findViewById(R.id.btnSignUp);

        // Set click listener for Login button
        loginButton.setOnClickListener(v -> {
            Intent loginIntent = new Intent(HomescreenActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        });

        // Set click listener for SignUp button
        signupButton.setOnClickListener(v -> {
            Intent signupIntent = new Intent(HomescreenActivity.this, RegisterActivity.class);
            startActivity(signupIntent);
        });
    }
}
