package com.example.voteinformed.ui.user;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.voteinformed.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class PersonalInfoActivity extends AppCompatActivity {

    private TextInputEditText inputUsername, inputEmail, inputPollSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        // --- Initialize Views ---
        ImageButton btnBack = findViewById(R.id.btnBackPersonalInfo);
        MaterialButton btnSave = findViewById(R.id.btnSaveChanges);

        inputUsername = findViewById(R.id.inputUsername);
        inputEmail = findViewById(R.id.inputEmail);
        inputPollSite = findViewById(R.id.inputPollSite);

        // --- Back Button Logic ---
        btnBack.setOnClickListener(v -> finish());

        // --- Save Button Logic ---
        btnSave.setOnClickListener(v -> {
            String name = inputUsername.getText().toString().trim();
            String email = inputEmail.getText().toString().trim();
            String pollSite = inputPollSite.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Name and Email are required", Toast.LENGTH_SHORT).show();
            } else {
                // Still needed: Save data to database/preferences
                Toast.makeText(this, "Information Updated", Toast.LENGTH_SHORT).show();
                finish(); // Close screen and return to profile
            }
        });
    }
}
