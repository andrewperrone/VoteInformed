package com.example.voteinformed.ui.previously_made;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.voteinformed.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class ProfileActivity extends AppCompatActivity {

    private View sliderBubble, sectionAbout, sectionPolicy, sectionContact;
    private MaterialButton tabAbout, tabPolicy, tabContact;

    private static final int COLOR_FILLED = 0xFF2ED1C2;   // aqua
    private static final int COLOR_OUTLINED = 0xFF3A4475; // indigo
    private static final int COLOR_STROKE = 0xFF8591C7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.politicianprofile);

        View root = findViewById(android.R.id.content);
        sliderBubble = findViewById(R.id.sliderBubble);
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets s = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(s.left, s.top, s.right, s.bottom);
            return insets;
        });

        sectionAbout = findViewById(R.id.sectionAbout);
        sectionPolicy = findViewById(R.id.sectionPolicy);
        sectionContact = findViewById(R.id.sectionContact);

        tabAbout = findViewById(R.id.tabAbout);
        tabPolicy = findViewById(R.id.tabPolicy);
        tabContact = findViewById(R.id.tabContact);

        MaterialButtonToggleGroup tabs = findViewById(R.id.tabs);

        // Default: About
        tabs.check(R.id.tabAbout);
        selectTab(tabAbout);
        showSection(sectionAbout);

        sliderBubble.post(() -> moveBubbleTo(tabAbout));

        tabs.addOnButtonCheckedListener((group, checkedId, isChecked) -> {


            if (!isChecked) return;

            if (checkedId == R.id.tabAbout) {
                selectTab(tabAbout);
                deselectTab(tabPolicy);
                deselectTab(tabContact);
                showSection(sectionAbout);
                moveBubbleTo(tabAbout);
            } else if (checkedId == R.id.tabPolicy) {
                selectTab(tabPolicy);
                deselectTab(tabAbout);
                deselectTab(tabContact);
                showSection(sectionPolicy);
                moveBubbleTo(tabPolicy);
            } else if (checkedId == R.id.tabContact) {
                selectTab(tabContact);
                deselectTab(tabAbout);
                deselectTab(tabPolicy);
                showSection(sectionContact);
                moveBubbleTo(tabContact);
            }
        });
    }

    private void showSection(View show) {
        sectionAbout.setVisibility(show == sectionAbout ? View.VISIBLE : View.GONE);
        sectionPolicy.setVisibility(show == sectionPolicy ? View.VISIBLE : View.GONE);
        sectionContact.setVisibility(show == sectionContact ? View.VISIBLE : View.GONE);
    }

    private void selectTab(MaterialButton b) {
        b.setBackgroundTintList(ColorStateList.valueOf(COLOR_FILLED));
        b.setStrokeWidth(0);
        b.setStrokeColor(ColorStateList.valueOf(0x00000000));
    }

    private void deselectTab(MaterialButton b) {
        b.setBackgroundTintList(ColorStateList.valueOf(COLOR_OUTLINED));
        int px = Math.round(getResources().getDisplayMetrics().density * 2); // 2dp
        b.setStrokeWidth(px);
        b.setStrokeColor(ColorStateList.valueOf(COLOR_STROKE));
    }
    private void moveBubbleTo(MaterialButton button) {
        sliderBubble.post(() -> {
            float targetX = button.getX() + dpToPx(4); // margin 4dp 감안
            sliderBubble.animate()
                    .x(targetX)
                    .setDuration(200)
                    .start();
        });
    }

    private float dpToPx(int dp) {
        return dp * getResources().getDisplayMetrics().density;
    }
}
