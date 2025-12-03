package com.example.voteinformed.data.api;
import retrofit2.http.Query;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.Call;

public interface CommitteeApi {
    @GET("bodies")
    Call<List<CommitteeDto>> getCommittees(
            @Query("token") String token,
            @Query("$filter") String filter
    );
}