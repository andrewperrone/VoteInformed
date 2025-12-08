package com.example.voteinformed.network;

import com.example.voteinformed.data.util.SocrataResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
import retrofit2.http.Query;

public interface PoliticianApiService {


    public static final String NYC_BASE_URL = "https://data.cityofnewyork.us/";

    @GET("resource/uvw5-9znb.json")
    Call<List<CouncilMember>> getCouncilMembers();

    public static final String NYS_BASE_URL = "https://legislation.nysenate.gov/";
    @GET("api/3/members/2025?full=true")
    Call<LegislatorResponse> getStateLegislators(@Query("key") String apiKey);

}