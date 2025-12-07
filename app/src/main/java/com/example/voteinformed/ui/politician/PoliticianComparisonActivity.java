package com.example.voteinformed.ui.politician;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.example.voteinformed.R;
import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.repository.VoteInformed_Repository;
import com.example.voteinformed.ui.search.PoliticianSearchDialog;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class PoliticianComparisonActivity extends AppCompatActivity implements PoliticianSearchDialog.OnPoliticianSelectedListener {

    private VoteInformed_Repository repository;
    private List<Politician> allPoliticians;

    private Politician leftPolitician;
    private Politician rightPolitician;

    private MaterialButton tabOverview, tabIssues, tabContact;
    private MaterialButton currentActiveTab;
    private ValueAnimator currentColorAnimator;
    private boolean isAnimating = false;

    private boolean selectingLeft = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politician_comparison);

        repository = new VoteInformed_Repository(getApplicationContext());

        initViews();
        setupInitialButtonStates();

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        findViewById(R.id.btnSwapLeft).setOnClickListener(v -> {
            selectingLeft = true;
            openSearchDialog();
        });
        findViewById(R.id.btnSwapRight).setOnClickListener(v -> {
            selectingLeft = false;
            openSearchDialog();
        });
        findViewById(R.id.btnRemoveLeft).setOnClickListener(v -> {
            leftPolitician = null;
            updateCard(true, null);
        });
        findViewById(R.id.btnRemoveRight).setOnClickListener(v -> {
            rightPolitician = null;
            updateCard(false, null);
        });

        loadPoliticians();
    }

    private void initViews() {
        tabOverview = findViewById(R.id.tabOverview);
        tabIssues = findViewById(R.id.tabIssues);
        tabContact = findViewById(R.id.tabContact);

        currentActiveTab = tabOverview;

        updateCard(true, null);
        updateCard(false, null);

        View.OnClickListener tabClick = v -> {
            MaterialButton clickedTab = (MaterialButton) v;
            if (clickedTab == tabOverview) switchTab(tabOverview, "overview");
            else if (clickedTab == tabIssues) switchTab(tabIssues, "issues");
            else if (clickedTab == tabContact) switchTab(tabContact, "contact");
        };

        tabOverview.setOnClickListener(tabClick);
        tabIssues.setOnClickListener(tabClick);
        tabContact.setOnClickListener(tabClick);
    }

    private void setupInitialButtonStates() {
        int blueColor = ContextCompat.getColor(this, R.color.app_primary_blue);
        int grayColor = Color.parseColor("#F5F5F5");
        int whiteText = ContextCompat.getColor(this, android.R.color.white);
        int darkText = ContextCompat.getColor(this, R.color.text_primary);

        tabOverview.setBackgroundColor(blueColor);
        tabOverview.setTextColor(whiteText);
        tabIssues.setBackgroundColor(grayColor);
        tabIssues.setTextColor(darkText);
        tabContact.setBackgroundColor(grayColor);
        tabContact.setTextColor(darkText);
    }

    private void switchTab(MaterialButton clickedTab, String tabType) {
        if (currentActiveTab == clickedTab || isAnimating) return;
        isAnimating = true;

        if (currentColorAnimator != null && currentColorAnimator.isRunning()) {
            currentColorAnimator.cancel();
        }

        animateButtonColors(currentActiveTab, clickedTab);
        currentActiveTab = clickedTab;

        if (leftPolitician != null) updateCard(true, leftPolitician);
        if (rightPolitician != null) updateCard(false, rightPolitician);
    }

    private void loadPoliticians() {
        repository.getAllPoliticians().observe(this, politicians -> {
            if (politicians != null && !politicians.isEmpty()) {
                this.allPoliticians = politicians;
            } else {
                Toast.makeText(this, "No politicians found.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openSearchDialog() {
        FragmentManager fm = getSupportFragmentManager();
        PoliticianSearchDialog dialog = PoliticianSearchDialog.newInstance();
        dialog.show(fm, "PoliticianSearchDialog");
    }

    @Override
    public void onPoliticianSelected(Politician politician) {
        if (politician == null) return;
        updateCard(selectingLeft, politician);
    }

    private void updateCard(boolean isLeft, Politician politician) {
        if (isLeft) leftPolitician = politician;
        else rightPolitician = politician;

        String side = isLeft ? "Left" : "Right";

        int nameId = getResources().getIdentifier("name" + side, "id", getPackageName());
        int partyId = getResources().getIdentifier("party" + side, "id", getPackageName());
        int imageId = getResources().getIdentifier("image" + side, "id", getPackageName());

        TextView tvName = findViewById(nameId);
        TextView tvParty = findViewById(partyId);
        ImageView ivImage = findViewById(imageId);

        if (politician == null) {
            tvName.setText("Empty Slot");
            tvParty.setText("Select a candidate");
            ivImage.setImageResource(R.drawable.user);
            clearAllRows(side);
            return;
        }

        tvName.setText(politician.getPolitician_name());
        tvParty.setText(politician.getPolitician_party());

        String url = politician.getPolitician_image_url();
        if (url != null && !url.equals("default_image") && !url.isEmpty()) {
            Glide.with(this).load(url).placeholder(R.drawable.user).into(ivImage);
        } else {
            ivImage.setImageResource(R.drawable.user);
        }

        if (currentActiveTab == tabOverview) {
            setMetricRow(side, 1, "Office Location", politician.getPolitician_location());
            setMetricRow(side, 2, "Party Affiliation", politician.getPolitician_party());
            clearOtherRows(side, 1, 2);
        } else if (currentActiveTab == tabIssues) {
            setMetricRow(side, 3, "Background", politician.getPolitician_background());
            setMetricRow(side, 4, "Key Positions", "Position data not available");
            clearOtherRows(side, 3, 4);
        } else if (currentActiveTab == tabContact) {
            setMetricRow(side, 5, "Contact Info", politician.getPolitician_contact());
            setMetricRow(side, 6, "Office Address", politician.getPolitician_location());
            setMetricRow(side, 7, "Website", politician.getPolitician_contact());
            clearOtherRows(side, 5, 7);
        }
    }

    private void clearAllRows(String side) {
        for (int i = 1; i <= 7; i++) setMetricRow(side, i, null, null);
    }

    private void clearOtherRows(String side, int start, int end) {
        for (int i = 1; i <= 7; i++) {
            if (i < start || i > end) setMetricRow(side, i, null, null);
        }
    }

    private void setMetricRow(String side, int index, String label, String content) {
        int layoutId = getResources().getIdentifier("metric" + side + index, "id", getPackageName());
        View row = findViewById(layoutId);

        if (row != null) {
            if (content == null || content.isEmpty()) {
                row.setVisibility(View.GONE);
            } else {
                row.setVisibility(View.VISIBLE);
                TextView tvLabel = row.findViewById(R.id.metricName);
                TextView tvContent = row.findViewById(R.id.metricContent);
                if (tvLabel != null) tvLabel.setText(label);
                if (tvContent != null) tvContent.setText(content);
            }
        }
    }

    private void animateButtonColors(MaterialButton fromButton, MaterialButton toButton) {
        int blueColor = ContextCompat.getColor(this, R.color.app_primary_blue);
        int grayColor = Color.parseColor("#F5F5F5");
        int whiteText = ContextCompat.getColor(this, android.R.color.white);
        int darkText = ContextCompat.getColor(this, R.color.text_primary);

        ValueAnimator fromBg = ValueAnimator.ofArgb(blueColor, grayColor);
        fromBg.setDuration(250);
        fromBg.setInterpolator(new android.view.animation.DecelerateInterpolator());
        fromBg.addUpdateListener(a -> fromButton.setBackgroundColor((int) a.getAnimatedValue()));
        fromBg.start();

        ValueAnimator fromText = ValueAnimator.ofArgb(whiteText, darkText);
        fromText.setDuration(250);
        fromText.addUpdateListener(a -> fromButton.setTextColor((int) a.getAnimatedValue()));
        fromText.start();

        currentColorAnimator = ValueAnimator.ofArgb(grayColor, blueColor);
        currentColorAnimator.setDuration(250);
        currentColorAnimator.setInterpolator(new android.view.animation.DecelerateInterpolator());
        currentColorAnimator.addUpdateListener(a -> toButton.setBackgroundColor((int) a.getAnimatedValue()));
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

        ValueAnimator toText = ValueAnimator.ofArgb(darkText, whiteText);
        toText.setDuration(250);
        toText.addUpdateListener(a -> toButton.setTextColor((int) a.getAnimatedValue()));
        toText.start();
    }
}
