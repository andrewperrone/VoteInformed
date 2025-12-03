package com.example.voteinformed.ui.politician;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.example.voteinformed.R;
import com.google.android.material.button.MaterialButton;

public class PoliticianProfileActivity extends AppCompatActivity {

    // Tab buttons
    private MaterialButton tabAbout, tabPolicy, tabContact;

    // Section cards corresponding to tabs
    private CardView sectionAbout, sectionPolicy, sectionContact;

    // Currently active section and tab
    private CardView currentSection;
    private MaterialButton currentActiveTab;

    // Animator for tab color transition
    private ValueAnimator currentColorAnimator;

    // Flag to prevent overlapping animations
    private boolean isAnimating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politician_profile);

        // Back button closes the activity
        ImageButton btnBack = findViewById(R.id.btnBackPolitician);
        btnBack.setOnClickListener(v -> finish());

        // Initialize tab buttons
        tabAbout = findViewById(R.id.tabAbout);
        tabPolicy = findViewById(R.id.tabPolicy);
        tabContact = findViewById(R.id.tabContact);

        // Initialize section cards
        sectionAbout = findViewById(R.id.sectionAbout);
        sectionPolicy = findViewById(R.id.sectionPolicy);
        sectionContact = findViewById(R.id.sectionContact);

        // Set default section and tab
        currentSection = sectionAbout;
        currentActiveTab = tabAbout;
        sectionAbout.setVisibility(View.VISIBLE);
        sectionPolicy.setVisibility(View.GONE);
        sectionContact.setVisibility(View.GONE);

        // Configure initial tab button colors
        setupInitialButtonStates();

        // Set click listeners for tabs
        tabAbout.setOnClickListener(v -> switchSection(sectionAbout, tabAbout));
        tabPolicy.setOnClickListener(v -> switchSection(sectionPolicy, tabPolicy));
        tabContact.setOnClickListener(v -> switchSection(sectionContact, tabContact));
    }

    /**
     * Sets up the initial tab button colors.
     * Active tab is blue with white text, others are gray with dark text.
     */
    private void setupInitialButtonStates() {
        int blueColor = ContextCompat.getColor(this, R.color.app_primary_blue);
        int grayColor = Color.parseColor("#F5F5F5");
        int whiteText = ContextCompat.getColor(this, android.R.color.white);
        int darkText = ContextCompat.getColor(this, R.color.text_primary);

        // About tab is active initially
        tabAbout.setBackgroundColor(blueColor);
        tabAbout.setTextColor(whiteText);

        // Other tabs inactive
        tabPolicy.setBackgroundColor(grayColor);
        tabPolicy.setTextColor(darkText);
        tabContact.setBackgroundColor(grayColor);
        tabContact.setTextColor(darkText);
    }

    /**
     * Handles switching between sections with animations.
     *
     * @param newSection The section to switch to
     * @param clickedTab The corresponding tab button
     */
    private void switchSection(CardView newSection, MaterialButton clickedTab) {
        // Ignore if already active or currently animating
        if (currentSection == newSection || isAnimating) return;

        isAnimating = true;

        // Cancel any ongoing animation
        if (currentColorAnimator != null && currentColorAnimator.isRunning()) {
            currentColorAnimator.cancel();
        }

        // Determine slide direction for animation
        boolean slideLeft = getSectionIndex(newSection) > getSectionIndex(currentSection);

        // Animate tab button color changes
        animateButtonColors(currentActiveTab, clickedTab);

        // Animate out current section
        animateOut(currentSection, slideLeft, () -> {
            currentSection.setVisibility(View.GONE);

            // Show new section with animation
            currentSection = newSection;
            newSection.setVisibility(View.VISIBLE);
            animateIn(newSection, slideLeft);
        });

        // Update currently active tab
        currentActiveTab = clickedTab;
    }

    /**
     * Animates the background and text colors of tabs during switch.
     */
    private void animateButtonColors(MaterialButton fromButton, MaterialButton toButton) {
        int blueColor = ContextCompat.getColor(this, R.color.app_primary_blue);
        int grayColor = Color.parseColor("#F5F5F5");
        int whiteText = ContextCompat.getColor(this, android.R.color.white);
        int darkText = ContextCompat.getColor(this, R.color.text_primary);

        // Animate old button: blue → gray
        ValueAnimator fromBgAnimator = ValueAnimator.ofArgb(blueColor, grayColor);
        fromBgAnimator.setDuration(500);
        fromBgAnimator.setInterpolator(new DecelerateInterpolator());
        fromBgAnimator.addUpdateListener(animation -> fromButton.setBackgroundColor((int) animation.getAnimatedValue()));
        fromBgAnimator.start();

        // Animate old button text: white → dark
        ValueAnimator fromTextAnimator = ValueAnimator.ofArgb(whiteText, darkText);
        fromTextAnimator.setDuration(500);
        fromTextAnimator.addUpdateListener(animation -> fromButton.setTextColor((int) animation.getAnimatedValue()));
        fromTextAnimator.start();

        // Animate new button: gray → blue
        currentColorAnimator = ValueAnimator.ofArgb(grayColor, blueColor);
        currentColorAnimator.setDuration(500);
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

        // Animate new button text: dark → white
        ValueAnimator toTextAnimator = ValueAnimator.ofArgb(darkText, whiteText);
        toTextAnimator.setDuration(500);
        toTextAnimator.addUpdateListener(animation -> toButton.setTextColor((int) animation.getAnimatedValue()));
        toTextAnimator.start();
    }

    /**
     * Animate a view sliding out horizontally with fade.
     *
     * @param view       The view to animate
     * @param slideLeft  Direction of slide
     * @param onComplete Callback when animation ends
     */
    private void animateOut(View view, boolean slideLeft, Runnable onComplete) {
        float endX = slideLeft ? -view.getWidth() : view.getWidth();

        view.animate()
                .translationX(endX)
                .alpha(0f)
                .setDuration(300)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setTranslationX(0);
                        if (onComplete != null) onComplete.run();
                    }
                })
                .start();
    }

    /**
     * Animate a view sliding in horizontally with fade.
     *
     * @param view      The view to animate
     * @param slideLeft Direction of slide
     */
    private void animateIn(View view, boolean slideLeft) {
        float startX = slideLeft ? view.getWidth() : -view.getWidth();
        view.setTranslationX(startX);
        view.setAlpha(0f);

        view.animate()
                .translationX(0)
                .alpha(1f)
                .setDuration(300)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(null)
                .start();
    }

    /**
     * Returns the index of a section for animation direction purposes.
     */
    private int getSectionIndex(CardView section) {
        if (section == sectionAbout) return 0;
        if (section == sectionPolicy) return 1;
        if (section == sectionContact) return 2;
        return 0;
    }
}
