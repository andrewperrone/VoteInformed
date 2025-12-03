package com.example.voteinformed.ui.previously_made;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.bumptech.glide.Glide;
import android.net.Uri;
import android.util.Log;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voteinformed.R;
import com.example.voteinformed.network.LegistarApiService;
import com.example.voteinformed.network.LegislationMatter;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.voteinformed.ui.concerns.ConcernsActivity;
import com.example.voteinformed.Article;
import com.example.voteinformed.NewsRepository;
import com.example.voteinformed.NewsResponse;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerLegislation;
    private ChipGroup chipGroupConcerns; // Removed year group
    private ImageButton btnLeftMenu, btnRightMenu;

    // YOUR API TOKEN
    private static final String API_TOKEN = "Uvxb0j9syjm3aI8h46DhQvnX5skN4aSUL0x_Ee3ty9M.ew0KICAiVmVyc2lvbiI6IDEsDQogICJOYW1lIjogIk5ZQyByZWFkIHRva2VuIDIwMTcxMDI2IiwNCiAgIkRhdGUiOiAiMjAxNy0xMC0yNlQxNjoyNjo1Mi42ODM0MDYtMDU6MDAiLA0KICAiV3JpdGUiOiBmYWxzZQ0KfQ";

    // Only filter by topic now, Year is always 2025
    private String selectedTopicFilter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 1. Initialize Views
        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerLegislation = findViewById(R.id.recyclerLegislation);
        chipGroupConcerns = findViewById(R.id.chipGroupConcerns);
        btnLeftMenu = findViewById(R.id.btnLeftMenu);
        btnRightMenu = findViewById(R.id.btnRightMenu);

        // 2. Setup RecyclerView
        recyclerLegislation.setLayoutManager(new LinearLayoutManager(this));

        // 3. Setup Buttons
        btnLeftMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        btnRightMenu.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
        });

        // 4. Setup Filters
        setupFilters();

        // 5. Load Data (Defaults to 2025)
        fetchLegislation();
    }

    private void setupFilters() {
        // --- CONCERN FILTER ---
        chipGroupConcerns.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.chipHealth) {
                selectedTopicFilter = "and substringof('Health', MatterBodyName)";
            } else if (checkedId == R.id.chipCrime) {
                selectedTopicFilter = "and substringof('Public Safety', MatterBodyName)";
            } else if (checkedId == R.id.chipEnvironment) {
                selectedTopicFilter = "and substringof('Environmental', MatterBodyName)";
            } else if (checkedId == R.id.chipEducation) {
                selectedTopicFilter = "and substringof('Education', MatterBodyName)";
            } else if (checkedId == R.id.chipTransport) {
                selectedTopicFilter = "and substringof('Transportation', MatterBodyName)";
            } else {
                selectedTopicFilter = ""; // "All Topics"
            }
            fetchLegislation(); // Reload data
        });
    }

    private void fetchLegislation() {
        // Always fetch 2025
        String filter = "MatterIntroDate ge datetime'2025-01-01T00:00:00' " + selectedTopicFilter;

        LegistarApiService api = LegistarApiService.Companion.create();

        // Fetch top 20 items
        api.getMatters(API_TOKEN, filter, "MatterIntroDate desc", 20).enqueue(new Callback<List<LegislationMatter>>() {
            @Override
            public void onResponse(@NonNull Call<List<LegislationMatter>> call, @NonNull Response<List<LegislationMatter>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<LegislationMatter> bills = response.body();
                    recyclerLegislation.setAdapter(new LegislationAdapter(bills));
                } else {
                    Toast.makeText(HomeActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<LegislationMatter>> call, @NonNull Throwable t) {
                Toast.makeText(HomeActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                Log.e("HomeActivity", "API Failed", t);
            }
        });
    }

    // --- Adapter Class ---
    private static class LegislationAdapter extends RecyclerView.Adapter<LegislationAdapter.ViewHolder> {
        private final List<LegislationMatter> list;

        public LegislationAdapter(List<LegislationMatter> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_legislation, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            LegislationMatter item = list.get(position);
            holder.title.setText(item.getTitle());
            holder.status.setText(item.getStatus());
            holder.committee.setText(item.getCommittee() != null ? item.getCommittee() : "Committee Unknown");
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView title, status, committee;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.tvTitle);
                status = itemView.findViewById(R.id.tvStatus);
                committee = itemView.findViewById(R.id.tvCommittee);
            }
        NavigationView navView = findViewById(R.id.nav_view);

        // Highlight current item and make it non-clickable
        navView.setCheckedItem(R.id.nav_home);
        navView.getMenu().findItem(R.id.nav_home).setEnabled(false);

        // Setup header view with user info
        setupNavHeader(navView);

        // Top-left hamburger menu button
        ImageButton btnLeft = findViewById(R.id.btnLeftMenu);
        btnLeft.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        // Top-right profile button
        ImageButton btnRight = findViewById(R.id.btnRightMenu);
        btnRight.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class)));

        // "Voice Concerns" button
        Button voiceConcerns = findViewById(R.id.btnVoiceConcerns);
        voiceConcerns.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, ConcernsActivity.class)));

        // Load news articles into TOP CONCERNS grid only (leave Current Issues blank)
        loadHomeScreenArticles();

        // Navigation menu item clicks
        setupNavMenu(navView);
    }

    private void loadHomeScreenArticles() {
        NewsRepository newsRepo = new NewsRepository(this);
        newsRepo.getArticlesForConcern("New York City").enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().articles != null) {
                    List<Article> articles = response.body().articles;

                    // Load ONLY top 4 articles into TOP CONCERNS grid
                    // Current Issues (featuredimage, issue1image, issue2image) left BLANK for another API
                    for (int i = 0; i < Math.min(4, articles.size()); i++) {
                        Article article = articles.get(i);
                        ImageView imageView = findViewById(getTopArticleImageId(i));
                        loadArticleImage(imageView, article);
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e("HomeActivity", "Articles load failed", t);
            }
        });
    }

    private int getTopArticleImageId(int position) {
        switch (position) {
            case 0: return R.id.article1image;
            case 1: return R.id.article2image;
            case 2: return R.id.article3image;
            case 3: return R.id.article4image;
            default: return R.id.article1image;
        }
    }

    private void loadArticleImage(ImageView imageView, Article article) {
        Glide.with(this)
                .load(article.urlToImage)
                .placeholder(android.R.color.darker_gray)
                .error(android.R.color.darker_gray)
                .into(imageView);

        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.url));
            startActivity(intent);
        });
    }

    private void setupNavHeader(NavigationView navView) {
        if (navView.getHeaderCount() > 0) {
            android.view.View headerView = navView.getHeaderView(0);
            ImageView profileImage = headerView.findViewById(R.id.profile_image);
            TextView userName = headerView.findViewById(R.id.user_name);
            TextView userEmail = headerView.findViewById(R.id.user_email);

            userName.setText("John Doe");
            userEmail.setText("john.doe@example.com");
        }
    }

    private void setupNavMenu(NavigationView navView) {
        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            } else if (id == R.id.nav_search) {
                startActivity(new Intent(HomeActivity.this, SearchActivity.class));
            } else if (id == R.id.nav_saved) {
                startActivity(new Intent(HomeActivity.this, SavedActivity.class));
            } else if (id == R.id.nav_comparison) {
                startActivity(new Intent(HomeActivity.this, PoliticianComparisonActivity.class));
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            } else if (id == R.id.nav_sign_out) {
                startActivity(new Intent(HomeActivity.this, HomescreenActivity.class));
                finish();
            } else {
                return false;
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}