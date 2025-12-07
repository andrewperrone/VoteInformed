package com.example.voteinformed.ui.saved;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voteinformed.R;
import com.example.voteinformed.data.repository.BookmarkRepository;
import com.example.voteinformed.ui.elections.ElectionsActivity;
import com.example.voteinformed.ui.home.HomeActivity;
import com.example.voteinformed.ui.home.HomescreenActivity;
import com.example.voteinformed.ui.politician.PoliticianComparisonActivity;
import com.example.voteinformed.ui.search.SearchActivity;
import com.example.voteinformed.ui.user.ProfileActivity;
import com.google.android.material.navigation.NavigationView;

public class SavedActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private RecyclerView recyclerSaved;
    private SavedArticleAdapter savedAdapter;
    private BookmarkRepository bookmarkRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        bookmarkRepo = new BookmarkRepository(this);
        Log.d("SavedActivity", "Loaded " + bookmarkRepo.getBookmarkedArticles().size() + " bookmarks");

        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);

        navView.setCheckedItem(R.id.nav_saved);
        navView.getMenu().findItem(R.id.nav_saved).setEnabled(false);
        setupNavHeader();
        setupBackPressHandler();
        setupNavMenu();

        ImageButton btnLeft = findViewById(R.id.btnLeftMenu);
        btnLeft.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        ImageButton btnClose = findViewById(R.id.btnRightMenu);
        btnClose.setOnClickListener(v -> finish());

        recyclerSaved = findViewById(R.id.recyclerSaved);
        recyclerSaved.setLayoutManager(new LinearLayoutManager(this));
        savedAdapter = new SavedArticleAdapter(bookmarkRepo);
        recyclerSaved.setAdapter(savedAdapter);

        savedAdapter.updateBookmarks();
    }

    private void setupNavHeader() {
        if (navView.getHeaderCount() > 0) {
            View header = navView.getHeaderView(0);
            ImageView img = header.findViewById(R.id.profile_image);
            TextView name = header.findViewById(R.id.user_name);
            TextView email = header.findViewById(R.id.user_email);

            name.setText("John Doe");
            email.setText("john.doe@example.com");
        }
    }

    private void setupBackPressHandler() {
        OnBackPressedCallback callback = new OnBackPressedCallback(false) {
            @Override
            public void handleOnBackPressed() {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        };

        getOnBackPressedDispatcher().addCallback(this, callback);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                callback.setEnabled(true);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                callback.setEnabled(false);
            }
        });
    }

    private void setupNavMenu() {
        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_elections_home){
                startActivity(new Intent(this, ElectionsActivity.class));
            }
            else if (id == R.id.nav_home) {
                startActivity(new Intent(this, HomeActivity.class));
            } else if (id == R.id.nav_search) {
                startActivity(new Intent(this, SearchActivity.class));
            } else if (id == R.id.nav_saved) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            } else if (id == R.id.nav_comparison) {
                startActivity(new Intent(this, PoliticianComparisonActivity.class));
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
            } else if (id == R.id.nav_sign_out) {
                startActivity(new Intent(this, HomescreenActivity.class));
                finish();
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }
}
