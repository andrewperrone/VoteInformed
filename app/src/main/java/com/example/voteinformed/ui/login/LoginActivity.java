package com.example.voteinformed.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.voteinformed.R;
import com.example.voteinformed.data.repository.VoteInformed_Repository;
import com.example.voteinformed.ui.home.HomeActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    // Input fields for email and password
    private TextInputEditText inputEmail, inputPassword;
    private VoteInformed_Repository voteInformedRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        voteInformedRepository = new VoteInformed_Repository(getApplicationContext());

        // Enable edge-to-edge layout for full-screen effect
        EdgeToEdge.enable(this);

        // Set content layout
        setContentView(R.layout.login);

        // Adjust padding for system bars (status/navigation bars)
        View root = findViewById(android.R.id.content);
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets sysInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(sysInsets.left, sysInsets.top, sysInsets.right, sysInsets.bottom);
            return insets;
        });

        // Initialize input fields
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);

        // Initialize buttons
        MaterialButton btnLogin = findViewById(R.id.btnLogin);
        TextView signupLink = findViewById(R.id.signupLink);

        // Signup link redirects to RegisterActivity
        signupLink.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class))
        );

        // Login button click listener
        btnLogin.setOnClickListener(v -> handleLogin());
    }

    /**
     * Handles login logic
     * Currently performs basic validation and redirects to HomeActivity
     */
    private void handleLogin() {
        String email = inputEmail.getText() != null ? inputEmail.getText().toString().trim() : "";
        String password = inputPassword.getText() != null ? inputPassword.getText().toString() : "";

        // Check if email or password is empty
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        voteInformedRepository.login(email, password, success->{
            if (success){
                Toast.makeText(this,"Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
