package com.example.voteinformed.ui.previously_made;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.voteinformed.ProfileActivity;
import com.example.voteinformed.R;


public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        ImageButton btnLeft = findViewById(R.id.btnLeftMenu);
        btnLeft.setOnClickListener(v -> {
            Intent p = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(p);
        });
        //ImageButton btnRight = findViewById(R.id.btnRightMenu);
        /*
        btnRight.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, Setting.class);
            startActivity(intent);
        });
        */

        Button voiceConcerns = findViewById(R.id.btnVoiceConcerns);
        voiceConcerns.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ConcernsActivity.class);
            startActivity(intent);
        });

    }
}