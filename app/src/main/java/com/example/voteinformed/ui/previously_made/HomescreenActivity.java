package com.example.voteinformed.ui.previously_made;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.voteinformed.LoginActivity;
import com.example.voteinformed.R;
import com.google.android.material.button.MaterialButton;

import android.content.Intent;

public class HomescreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        View root = findViewById(android.R.id.content);
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets sys = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(sys.left, sys.top, sys.right, sys.bottom); // keep content visible
            return insets;
        });

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.homescreen);


        MaterialButton login = findViewById(R.id.btnLogin);
        MaterialButton signup = findViewById(R.id.btnSignUp);

        login.setOnClickListener(v -> {
            Intent i = new Intent(HomescreenActivity.this, LoginActivity.class);
            startActivity(i);
        });

        signup.setOnClickListener(v -> {
            Intent i = new Intent(HomescreenActivity.this, RegisterActivity.class);
            startActivity(i);
        });
    }
}
