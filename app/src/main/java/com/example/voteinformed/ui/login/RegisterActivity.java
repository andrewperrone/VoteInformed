package com.example.voteinformed.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voteinformed.R;
import com.example.voteinformed.data.repository.VoteInformed_Repository;
import com.example.voteinformed.ui.home.HomeActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText inputEmail, inputPassword, inputConfirmPassword;
    private VoteInformed_Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        repository = new VoteInformed_Repository(getApplicationContext());

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);

        MaterialButton btnSignUp = findViewById(R.id.btnSignUpPrimary);
        TextView loginLink = findViewById(R.id.loginLink);

        loginLink.setOnClickListener(v ->
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class))
        );

        btnSignUp.setOnClickListener(v -> {
            String email = inputEmail.getText().toString().trim();
            String pass = inputPassword.getText().toString();
            String confirm = inputConfirmPassword.getText().toString();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(confirm)) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pass.equals(confirm)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Register the user
            repository.register(email, pass, (success, emailExists) -> {
                if (emailExists) {
                    Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show();
                } else if (success) {
                    //  autologin to get the User ID
                    performAutoLogin(email, pass);
                } else {
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void performAutoLogin(String email, String password) {
        repository.login(email, password, user -> {
            if (user != null) {
                // SUPER IMPORTANT: Save User ID to SharedPreferences
                SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
                prefs.edit().putInt("user_id", user.getUser_id()).apply();

                Toast.makeText(this, "Account Created!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Auto-login failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}