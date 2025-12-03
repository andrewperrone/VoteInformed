package com.example.voteinformed.ui.previously_made;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.voteinformed.R;
import com.example.voteinformed.database.Users;
import com.example.voteinformed.database.VoteInformedDatabase;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText inputUsername, inputEmail, inputPassword, inputConfirmPassword;
    private VoteInformedDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        db = VoteInformedDatabase.getDatabase(getApplicationContext());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputUsername = findViewById(R.id.inputUsername);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);

        MaterialButton btnSignUp = findViewById(R.id.btnSignUpPrimary);
        TextView loginLink = findViewById(R.id.loginLink);

        loginLink.setOnClickListener(v ->
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class))
        );

        btnSignUp.setOnClickListener(v -> {
            String username = inputUsername.getText() != null ? inputUsername.getText().toString().trim() : "";
            String email = inputEmail.getText() != null ? inputEmail.getText().toString().trim() : "";
            String password = inputPassword.getText() != null ? inputPassword.getText().toString() : "";
            String confirmPassword = inputConfirmPassword.getText() != null ? inputConfirmPassword.getText().toString() : "";

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            VoteInformedDatabase.databaseWriteExecutor.execute(() -> {
                // Proactive checks for better UX, avoiding exceptions for flow control
                if (db.usersDao().getUserByUsername(username) != null) {
                    runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "Username already exists", Toast.LENGTH_SHORT).show());
                    return;
                }

                if (db.usersDao().getUserByEmail(email) != null) {
                    runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "Email already registered", Toast.LENGTH_SHORT).show());
                    return;
                }

                try {
                    Users newUser = new Users(username, password, email);
                    db.usersDao().insert(newUser);
                    // On success, navigate to home
                    runOnUiThread(() -> {
                        Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                        finish();
                    });
                } catch (SQLiteConstraintException e) {
                    // This is the safety net for the race condition
                    runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "Username or email already exists.", Toast.LENGTH_SHORT).show());
                }
            });
        });
    }
}
