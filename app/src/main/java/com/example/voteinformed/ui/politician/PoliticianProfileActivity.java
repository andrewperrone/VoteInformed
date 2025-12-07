package com.example.voteinformed.ui.politician;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.voteinformed.R;
import com.example.voteinformed.data.repository.VoteInformed_Repository;
import com.example.voteinformed.ui.home.HomeActivity;
import com.example.voteinformed.ui.saved.SavedActivity;
import com.example.voteinformed.ui.search.SearchActivity;
import com.example.voteinformed.ui.user.ProfileActivity;
import com.example.voteinformed.ui.politician.PoliticianComparisonActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

public class PoliticianProfileActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    private MaterialButton tabAbout, tabPolicy, tabContact;
    private CardView sectionAbout, sectionPolicy, sectionContact;
    private CardView currentSection;
    private MaterialButton currentActiveTab;


    private ImageView imgProfile;
    private TextView tvName, tvParty, tvAboutContent, tvContactLocation, tvContactDetails;


    private ValueAnimator currentColorAnimator;
    private boolean isAnimating = false;
    private VoteInformed_Repository repository;

    private View fabCompare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politician_profile);

        repository = new VoteInformed_Repository(getApplicationContext());

        initViews();
        initDrawerMenu();
        setupInitialButtonStates();

        int politicianId = getIntent().getIntExtra("politician_id", -1);
        if (politicianId != -1) loadData(politicianId);
    }

    private void initDrawerMenu() {
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);

        setupNavHeader(navView);

        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                startActivity(new Intent(this, HomeActivity.class));
            } else if (id == R.id.nav_search) {
                startActivity(new Intent(this, SearchActivity.class));
            } else if (id == R.id.nav_saved) {
                startActivity(new Intent(this, SavedActivity.class));
            } else if (id == R.id.nav_comparison) {
                startActivity(new Intent(this, PoliticianComparisonActivity.class));
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
            } else if (id == R.id.nav_sign_out) {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void setupNavHeader(NavigationView navView) {
        if (navView.getHeaderCount() > 0) {
            View header = navView.getHeaderView(0);
            TextView username = header.findViewById(R.id.user_name);
            TextView email = header.findViewById(R.id.user_email);

            SharedPreferences prefs = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
            int userId = prefs.getInt("user_id", -1);

            if (userId != -1) {
                VoteInformed_Repository repo = new VoteInformed_Repository(getApplicationContext());
                repo.getUserById(userId).observe(this, user -> {
                    if (user != null) {
                        username.setText(user.getName());
                        email.setText(user.getEmail());
                    }
                });
            } else {
                username.setText("Guest");
                email.setText("Please log in");
            }
        }
    }

    private void initViews() {

        ImageButton btnLeftMenu = findViewById(R.id.btnLeftMenu);
        btnLeftMenu.setOnClickListener(v ->
                drawerLayout.openDrawer(GravityCompat.START)
        );

        ImageButton btnBack = findViewById(R.id.btnBackPolitician);
        btnBack.setOnClickListener(v -> finish());

        fabCompare = findViewById(R.id.fabCompare);
        fabCompare.setOnClickListener(v -> onCompareClicked());

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

        currentSection = sectionAbout;
        currentActiveTab = tabAbout;

        tabAbout.setOnClickListener(v -> switchSection(sectionAbout, tabAbout));
        tabPolicy.setOnClickListener(v -> switchSection(sectionPolicy, tabPolicy));
        tabContact.setOnClickListener(v -> switchSection(sectionContact, tabContact));
    }

    private void onCompareClicked() {
        Intent intent = new Intent(this, PoliticianComparisonActivity.class);

        int pid = getIntent().getIntExtra("politician_id", -1);
        intent.putExtra("politician_id", pid);

        startActivity(intent);
    }


    private void loadData(int id) {
        repository.getPoliticianById(id).observe(this, politician -> {
            if (politician != null) {
                tvName.setText(politician.getPolitician_name());
                tvParty.setText(politician.getPolitician_party());
                tvAboutContent.setText(politician.getPolitician_background());
                tvContactLocation.setText(politician.getPolitician_location());
                tvContactDetails.setText(politician.getPolitician_contact());

                String img = politician.getPolitician_image_url();
                if (img != null && !img.equals("default_image") && !img.isEmpty()) {
                    Glide.with(this).load(img).placeholder(R.drawable.user).into(imgProfile);
                } else {
                    imgProfile.setImageResource(R.drawable.user);
                }
            }
        });
    }

    private void setupInitialButtonStates() {
        int blue = ContextCompat.getColor(this, R.color.app_primary_blue);
        int gray = Color.parseColor("#F5F5F5");
        int white = ContextCompat.getColor(this, android.R.color.white);
        int dark = ContextCompat.getColor(this, R.color.text_primary);

        tabAbout.setBackgroundColor(blue);
        tabAbout.setTextColor(white);
        tabPolicy.setBackgroundColor(gray);
        tabPolicy.setTextColor(dark);
        tabContact.setBackgroundColor(gray);
        tabContact.setTextColor(dark);
    }

    private void switchSection(CardView newSection, MaterialButton clickedTab) {
        if (currentSection == newSection || isAnimating) return;

        isAnimating = true;

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

    private void animateButtonColors(MaterialButton fromBtn, MaterialButton toBtn) {
        int blue = ContextCompat.getColor(this, R.color.app_primary_blue);
        int gray = Color.parseColor("#F5F5F5");
        int white = ContextCompat.getColor(this, android.R.color.white);
        int dark = ContextCompat.getColor(this, R.color.text_primary);

        ValueAnimator bg1 = ValueAnimator.ofArgb(blue, gray);
        bg1.setDuration(300);
        bg1.addUpdateListener(a -> fromBtn.setBackgroundColor((int) a.getAnimatedValue()));
        bg1.start();

        ValueAnimator text1 = ValueAnimator.ofArgb(white, dark);
        text1.setDuration(300);
        text1.addUpdateListener(a -> fromBtn.setTextColor((int) a.getAnimatedValue()));
        text1.start();

        ValueAnimator bg2 = ValueAnimator.ofArgb(gray, blue);
        bg2.setDuration(300);
        bg2.addUpdateListener(a -> toBtn.setBackgroundColor((int) a.getAnimatedValue()));
        bg2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                isAnimating = false;
            }
        });
        bg2.start();

        ValueAnimator text2 = ValueAnimator.ofArgb(dark, white);
        text2.setDuration(300);
        text2.addUpdateListener(a -> toBtn.setTextColor((int) a.getAnimatedValue()));
        text2.start();
    }

    private void animateOut(View v, boolean slideLeft, Runnable done) {
        float endX = slideLeft ? -v.getWidth() : v.getWidth();
        v.animate().translationX(endX).alpha(0f).setDuration(250)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator a) {
                        v.setTranslationX(0);
                        if (done != null) done.run();
                    }
                }).start();
    }

    private void animateIn(View v, boolean slideLeft) {
        v.setTranslationX(slideLeft ? v.getWidth() : -v.getWidth());
        v.setAlpha(0f);

        v.animate().translationX(0).alpha(1f).setDuration(250)
                .setInterpolator(new DecelerateInterpolator()).start();
    }

    private int getSectionIndex(CardView section) {
        if (section == sectionAbout) return 0;
        if (section == sectionPolicy) return 1;
        return 2;
    }
}
