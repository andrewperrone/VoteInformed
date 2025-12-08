package com.example.voteinformed.data.repository;

import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.util.SocrataResponse;
import com.example.voteinformed.data.dao.Politician_Dao;
import com.example.voteinformed.network.CouncilMember;
import com.example.voteinformed.network.PoliticianApiService;
import com.example.voteinformed.network.LegislatorResponse;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.ArrayList;
import java.util.List;

public class PoliticianNetworkRepository {

    private static final String TAG = "PoliticianNetworkRepo";

    // API Key for NYS Legislation, No stealing pls
    private static final String NYS_API_KEY = "E74184CMgpcI2ygbxR79r8wGj9HrCXSY";

    private final PoliticianApiService nycApiService;
    private final PoliticianApiService nysApiService;
    private final Politician_Dao politicianDao;

    public PoliticianNetworkRepository(Politician_Dao dao) {
        this.politicianDao = dao;

        // Separate Retrofit instances due to different base URLs

        // Retrofit for NYC Council API
        Retrofit nycRetrofit = new Retrofit.Builder()
                .baseUrl(PoliticianApiService.NYC_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.nycApiService = nycRetrofit.create(PoliticianApiService.class);

        // Retrofit for NYS Legislation API
        Retrofit nysRetrofit = new Retrofit.Builder()
                .baseUrl(PoliticianApiService.NYS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.nysApiService = nysRetrofit.create(PoliticianApiService.class);
    }

    // Public trigger for NYC Council Members
    public void fetchAndSaveCouncilMembers() {
        nycApiService.getCouncilMembers().enqueue(new Callback<List<CouncilMember>>() {
            @Override
            public void onResponse(Call<List<CouncilMember>> call, Response<List<CouncilMember>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Politician> newPoliticians = parseSocrataResponse(response.body());
                    insertPoliticiansAsync(newPoliticians, "NYC Council Members");
                } else {
                    Log.e(TAG, "NYC Council API failed: " + response.code() + " " + response.message());
                }
            }
            @Override
            public void onFailure(Call<List<CouncilMember>> call, Throwable t) {
                Log.e(TAG, "NYC Council API connection failed: " + t.getMessage(), t);
            }
        });
    }

    // Public trigger for NYS State Legislators
    public void fetchAndSaveStateLegislators() {
        nysApiService.getStateLegislators(NYS_API_KEY).enqueue(new Callback<LegislatorResponse>() {
            @Override
            public void onResponse(Call<LegislatorResponse> call, Response<LegislatorResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().result != null) {
                    List<Politician> legislators = parseNYSLegislatorResponse(response.body().result.items);
                    insertPoliticiansAsync(legislators, "NYS Legislators");
                } else {
                    Log.e(TAG, "NYS API failed: " + response.code() + " " + response.message());
                }
            }
            @Override
            public void onFailure(Call<LegislatorResponse> call, Throwable t) {
                Log.e(TAG, "NYS API connection failed: " + t.getMessage(), t);
            }
        });
    }

    //Inserts politicians to db
    //Note: we aren't using deleteAll() to preserve static data
    private void insertPoliticiansAsync(List<Politician> politicians, String source) {
        if (!politicians.isEmpty()) {
            new Thread(() -> {
                // insert each politician
                for (Politician p : politicians) {
                    politicianDao.insert(p);
                }
                Log.d(TAG, "Successfully saved " + politicians.size() + " " + source + ".");
            }).start();
        }
    }

    // Parser for NYC Council
    private List<Politician> parseSocrataResponse(List<CouncilMember> members) {
        List<Politician> politicians = new ArrayList<>();

        for (CouncilMember member : members) {

            String name = member.name != null ? member.name : "Unknown Member";
            String district = member.district != null ? member.district : "N/A";
            String borough = member.borough != null ? member.borough : "N/A";


            String rawParty = member.politicalParty != null ? member.politicalParty : "Independent";
            String party = "Independent";
            if (rawParty.equalsIgnoreCase("Democrat") || rawParty.equalsIgnoreCase("Democratic")) {
                party = "Democratic";
            } else if (rawParty.equalsIgnoreCase("Republican")) {
                party = "Republican";
            } else {
                party = rawParty;
            }

            String location = "NYC Council, District " + district + " (" + borough + ")";
            String contact = "Contact District Office " + district + " for details.";
            String background = "New York City Council Member, representing District " + district + " in " + borough + ".";
            String imageUrl = "default_image";

            Politician p = new Politician(name, party, imageUrl, contact, background, location);
            politicians.add(p);
        }
        return politicians;
    }

    // Parser for NYS Legislators, uses custom JSON mapping
    private List<Politician> parseNYSLegislatorResponse(List<LegislatorResponse.Member> members) {
        List<Politician> politicians = new ArrayList<>();

        if (members == null) return politicians;

        for (LegislatorResponse.Member member : members) {
            String name = member.memberFullName;
            String party = mapParty(member.party);
            String chamber = member.chamber.toLowerCase().contains("senate") ? "Senate" : "Assembly";
            String district = member.districtCode;

            String location = String.format("NYS %s, District %s", chamber, district);
            String background = member.shortDescription != null && !member.shortDescription.isEmpty()
                    ? member.shortDescription
                    : String.format("New York State %s Member, representing District %s.", chamber, district);

            String contact = String.format("Contact NYS %s office for District %s.", chamber, district);
            String imageUrl = "default_image";

            Politician p = new Politician(name, party, imageUrl, contact, background, location);
            politicians.add(p);
        }
        return politicians;
    }

    private String mapParty(String partyAbbr) {
        if (partyAbbr == null) return "Independent";
        switch (partyAbbr.toUpperCase()) {
            case "D":
            case "DEM": return "Democratic";
            case "R":
            case "REP": return "Republican";
            default: return "Independent";
        }
    }
}