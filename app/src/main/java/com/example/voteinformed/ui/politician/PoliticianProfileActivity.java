package com.example.voteinformed.ui.politician;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.voteinformed.R;
import com.example.voteinformed.data.repository.VoteInformed_Repository;
import com.google.android.material.button.MaterialButton;

public class PoliticianProfileActivity extends AppCompatActivity {

    // Views
    private MaterialButton tabAbout, tabPolicy, tabContact;
    private CardView sectionAbout, sectionPolicy, sectionContact;
    private CardView currentSection;
    private MaterialButton currentActiveTab;
    private ImageView imgProfile;
    private TextView tvName, tvParty, tvAboutContent, tvContactLocation, tvContactDetails;

    // Logic
    private ValueAnimator currentColorAnimator;
    private boolean isAnimating = false;
    private VoteInformed_Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politician_profile);

        repository = new VoteInformed_Repository(getApplicationContext());

        // Initialize Views
        initViews();

        // Configure initial tab button colors
        setupInitialButtonStates();

        // Get ID and Load Data
        int politicianId = getIntent().getIntExtra("politician_id", -1);
        if (politicianId != -1) {
            loadData(politicianId);
        } else {
            Toast.makeText(this, "Error: No politician selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void initViews() {
        ImageButton btnBack = findViewById(R.id.btnBackPolitician);
        btnBack.setOnClickListener(v -> finish());

        tabAbout = findViewById(R.id.tabAbout);
        tabPolicy = findViewById(R.id.tabPolicy);
        tabContact = findViewById(R.id.tabContact);

        sectionAbout = findViewById(R.id.sectionAbout);
        sectionPolicy = findViewById(R.id.sectionPolicy);
        sectionContact = findViewById(R.id.sectionContact);

        imgProfile = findViewById(R.id.imgPoliticianProfile);
        tvName = findViewById(R.id.tvPoliticianName);
        tvParty = findViewById(R.id.tvPoliticianParty);
        tvAboutContent = findViewById(R.id.tvAboutContent);
        tvContactLocation = findViewById(R.id.tvContactLocation);
        tvContactDetails = findViewById(R.id.tvContactDetails);

        // Set default section
        currentSection = sectionAbout;
        currentActiveTab = tabAbout;
        sectionAbout.setVisibility(View.VISIBLE);
        sectionPolicy.setVisibility(View.GONE);
        sectionContact.setVisibility(View.GONE);

        // Click listeners
        tabAbout.setOnClickListener(v -> switchSection(sectionAbout, tabAbout));
        tabPolicy.setOnClickListener(v -> switchSection(sectionPolicy, tabPolicy));
        tabContact.setOnClickListener(v -> switchSection(sectionContact, tabContact));
    }

    private void loadData(int id) {
        repository.getPoliticianById(id).observe(this, politician -> {
            if (politician != null) {
                // Header
                tvName.setText(politician.getPolitician_name());
                tvParty.setText(politician.getPolitician_party());

                // Image
                String url = politician.getPolitician_image_url();
                if (url != null && !url.equals("default_image") && !url.isEmpty()) {
                    Glide.with(this).load(url).placeholder(R.drawable.user).into(imgProfile);
                } else {
                    imgProfile.setImageResource(R.drawable.user);
                }

                // Tabs Content
                tvAboutContent.setText(politician.getPolitician_background());
                tvContactLocation.setText(politician.getPolitician_location());
                tvContactDetails.setText(politician.getPolitician_contact());
            }
        });
    }

    // Tabs
    private void setupInitialButtonStates() {
        int blueColor = ContextCompat.getColor(this, R.color.app_primary_blue);
        int grayColor = Color.parseColor("#F5F5F5");
        int whiteText = ContextCompat.getColor(this, android.R.color.white);
        int darkText = ContextCompat.getColor(this, R.color.text_primary);

        tabAbout.setBackgroundColor(blueColor);
        tabAbout.setTextColor(whiteText);
        tabPolicy.setBackgroundColor(grayColor);
        tabPolicy.setTextColor(darkText);
        tabContact.setBackgroundColor(grayColor);
        tabContact.setTextColor(darkText);
    }

    private void switchSection(CardView newSection, MaterialButton clickedTab) {
        if (currentSection == newSection || isAnimating) return;
        isAnimating = true;

        if (currentColorAnimator != null && currentColorAnimator.isRunning()) {
            currentColorAnimator.cancel();
        }

        boolean slideLeft = getSectionIndex(newSection) > getSectionIndex(currentSection);
        animateButtonColors(currentActiveTab, clickedTab);

        animateOut(currentSection, slideLeft, () -> {
            currentSection.setVisibility(View.GONE);
            currentSection = newSection;
            newSection.setVisibility(View.VISIBLE);
            animateIn(newSection, slideLeft);
        });
        currentActiveTab = clickedTab;
    }

    private void animateButtonColors(MaterialButton fromButton, MaterialButton toButton) {
        int blueColor = ContextCompat.getColor(this, R.color.app_primary_blue);
        int grayColor = Color.parseColor("#F5F5F5");
        int whiteText = ContextCompat.getColor(this, android.R.color.white);
        int darkText = ContextCompat.getColor(this, R.color.text_primary);

        ValueAnimator fromBgAnimator = ValueAnimator.ofArgb(blueColor, grayColor);
        fromBgAnimator.setDuration(300);
        fromBgAnimator.setInterpolator(new DecelerateInterpolator());
        fromBgAnimator.addUpdateListener(animation -> fromButton.setBackgroundColor((int) animation.getAnimatedValue()));
        fromBgAnimator.start();

        ValueAnimator fromTextAnimator = ValueAnimator.ofArgb(whiteText, darkText);
        fromTextAnimator.setDuration(300);
        fromTextAnimator.addUpdateListener(animation -> fromButton.setTextColor((int) animation.getAnimatedValue()));
        fromTextAnimator.start();

        currentColorAnimator = ValueAnimator.ofArgb(grayColor, blueColor);
        currentColorAnimator.setDuration(300);
        currentColorAnimator.setInterpolator(new DecelerateInterpolator());
        currentColorAnimator.addUpdateListener(animation -> toButton.setBackgroundColor((int) animation.getAnimatedValue()));
        currentColorAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimating = false;
            }
            @Override
            public void onAnimationCancel(Animator animation) {
                isAnimating = false;
            }
        });
        currentColorAnimator.start();

        ValueAnimator toTextAnimator = ValueAnimator.ofArgb(darkText, whiteText);
        toTextAnimator.setDuration(300);
        toTextAnimator.addUpdateListener(animation -> toButton.setTextColor((int) animation.getAnimatedValue()));
        toTextAnimator.start();
    }

    private void animateOut(View view, boolean slideLeft, Runnable onComplete) {
        float endX = slideLeft ? -view.getWidth() : view.getWidth();
        view.animate().translationX(endX).alpha(0f).setDuration(250)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setTranslationX(0);
                        if (onComplete != null) onComplete.run();
                    }
                }).start();
    }

    private void animateIn(View view, boolean slideLeft) {
        float startX = slideLeft ? view.getWidth() : -view.getWidth();
        view.setTranslationX(startX);
        view.setAlpha(0f);
        view.animate().translationX(0).alpha(1f).setDuration(250)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(null).start();
    }

    private int getSectionIndex(CardView section) {
        if (section == sectionAbout) return 0;
        if (section == sectionPolicy) return 1;
        if (section == sectionContact) return 2;
        return 0;
    }
}