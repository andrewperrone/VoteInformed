package com.example.voteinformed.ui.elections;

import android.util.Log;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voteinformed.R;
import com.example.voteinformed.model.CivicElectionResponse;
import com.example.voteinformed.model.ElectionModel;
import com.example.voteinformed.model.VoterInfoResponse;
import com.example.voteinformed.network.CivicApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import com.example.voteinformed.network.CivicApiClient;
import com.example.voteinformed.model.PollingLocationModel;
import com.example.voteinformed.model.AddressModel;

import android.content.Intent;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.voteinformed.ui.home.HomeActivity;
import com.example.voteinformed.ui.home.HomescreenActivity;
import com.example.voteinformed.ui.politician.FeaturedPoliticianAdapter;
import com.example.voteinformed.ui.politician.FeaturedPoliticianViewModel;
import com.example.voteinformed.ui.politician.PoliticianComparisonActivity;
import com.example.voteinformed.ui.saved.SavedActivity;
import com.example.voteinformed.ui.search.SearchActivity;
import com.google.android.material.navigation.NavigationView;
import com.example.voteinformed.ui.user.ProfileActivity;
import com.google.android.material.textfield.TextInputEditText;

public class ElectionsActivity extends AppCompatActivity {

    private EditText addressInput;
    private Button searchButton;
    private TextView resultText;
    private RecyclerView electionsRecyclerView;

    private RecyclerView politicianRecyclerView;
    private FeaturedPoliticianAdapter featuredPoliticianAdapter;
    private String API_KEY;
    private CivicApiService apiService;

    private DrawerLayout drawerLayout;
    private ImageButton btnLeftMenu, btnRightMenu;
    private NavigationView navView;




    private com.example.voteinformed.ui.elections.ElectionsAdapter electionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elections);

        API_KEY = getString(R.string.google_civic_api_key);

        addressInput = findViewById(R.id.address_input);
        searchButton = findViewById(R.id.search_poll_site_button);
        resultText = findViewById(R.id.poll_site_result);
        electionsRecyclerView = findViewById(R.id.elections_recycler_view);
        //election page politicians cards
        politicianRecyclerView = findViewById(R.id.politician_recycler_view);

        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        btnLeftMenu = findViewById(R.id.btnLeftMenu);
        btnRightMenu = findViewById(R.id.btnRightMenu);

        btnLeftMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        btnRightMenu.setOnClickListener(v -> startActivity(new Intent(ElectionsActivity.this, ProfileActivity.class)));
        setupNavMenu(navView);

        apiService = CivicApiClient.getClient();


        electionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        politicianRecyclerView.setLayoutManager(gridLayoutManager);

        electionsAdapter = new ElectionsAdapter(new ArrayList<>());
        electionsRecyclerView.setAdapter(electionsAdapter);


        fetchUpcomingElections();

        searchButton.setOnClickListener(v -> searchPollSite());

        TextInputEditText inputSearch = findViewById(R.id.inputSearch);
        inputSearch.setFocusable(false);
        inputSearch.setOnClickListener(v -> {
            Intent intent = new Intent(ElectionsActivity.this, SearchActivity.class);
            startActivity(intent);
        });

       FeaturedPoliticianViewModel viewModel =
                new ViewModelProvider(this).get(FeaturedPoliticianViewModel.class);

        featuredPoliticianAdapter = new FeaturedPoliticianAdapter();
        politicianRecyclerView.setAdapter(featuredPoliticianAdapter);

        viewModel.getRandomPoliticians().observe(this,
                politicians -> featuredPoliticianAdapter.setPoliticians(politicians));
    }

    private void fetchUpcomingElections() {
        apiService.getElections(API_KEY).enqueue(new Callback<CivicElectionResponse>() {
            @Override
            public void onResponse(Call<CivicElectionResponse> call, Response<CivicElectionResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ElectionModel> elections = response.body().getElections();
                    if (elections != null) {
                        // Update the adapter with the fetched data
                        electionsAdapter.updateElections(elections);
                    }
                } else {
                    Toast.makeText(ElectionsActivity.this, "Failed to load elections: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CivicElectionResponse> call, Throwable t) {
                Toast.makeText(ElectionsActivity.this, "Network Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void searchPollSite() {
        String address = addressInput.getText().toString().trim();
        if (address.isEmpty()) {
            addressInput.setError("Address is required");
            return;
        }

        resultText.setText("Searching for polling location...");

        apiService.getVoterInfo(address, "0", false, API_KEY)
                .enqueue(new Callback<VoterInfoResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<VoterInfoResponse> call,
                                           @NonNull Response<VoterInfoResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            VoterInfoResponse info = response.body();

                            if (info.getPollingLocations() != null && !info.getPollingLocations().isEmpty()) {

                                PollingLocationModel pollingLocation = info.getPollingLocations().get(0);
                                AddressModel address = pollingLocation.getAddress();

                                // Build the result string
                                StringBuilder result = new StringBuilder();
                                result.append("Polling Site Found!\n\n");
                                result.append("Location: ").append(address.getLocationName()).append("\n");
                                result.append("Address: ").append(address.getFullAddress()).append("\n");

                                String hours = pollingLocation.getPollingHours();
                                if (hours != null && !hours.isEmpty()) {
                                    result.append("Hours: ").append(hours).append("\n");
                                }
                                String notes = pollingLocation.getNotes();
                                if (notes != null && !notes.isEmpty()) {
                                    result.append("Notes: ").append(notes).append("\n");
                                }

                                resultText.setText(result.toString());

                            } else if (info.getPollingLocations() == null || info.getPollingLocations().isEmpty()) {
                                resultText.setText("No Election Day Polling Location found for this address. Check for early voting sites or contact your local election office.");
                            } else {
                                resultText.setText("No voter information is available for the given address.");
                            }
                        } else {
                            resultText.setText("Error retrieving voter info. Please check the address or try again later. HTTP Code: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<VoterInfoResponse> call, @NonNull Throwable t) {
                        resultText.setText("Network Error: Failed to connect to Civic Information service. " + t.getMessage());
                        Log.e("ElectionsActivity", "Voter Info API Failed", t);
                    }
                });
    }

    private void setupNavMenu(NavigationView navView) {
        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            Intent intent = null;

            if (id == R.id.nav_elections_home) {
                // We are already on the Elections screen (new homepage). Just close the drawer.
            } else if (id == R.id.nav_home) {
                // Navigate to the Articles & Legislatures screen (old homepage)
                intent = new Intent(ElectionsActivity.this, HomeActivity.class);
            } else if (id == R.id.nav_search) {
                intent = new Intent(ElectionsActivity.this, SearchActivity.class);
            } else if (id == R.id.nav_saved) {
                intent = new Intent(ElectionsActivity.this, SavedActivity.class);
            } else if (id == R.id.nav_comparison) {
                intent = new Intent(ElectionsActivity.this, PoliticianComparisonActivity.class);
            } else if (id == R.id.nav_profile) {
                intent = new Intent(ElectionsActivity.this, ProfileActivity.class);
            } else if (id == R.id.nav_sign_out) {
                // Assuming HomescreenActivity is the initial Login/Splash screen
                intent = new Intent(ElectionsActivity.this, HomescreenActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            } else {
                return false;
            }

            if (intent != null) {
                startActivity(intent);
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }
}