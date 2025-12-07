package com.example.voteinformed.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import com.example.voteinformed.NewsResponse;
import com.example.voteinformed.R;
import com.example.voteinformed.network.NewsApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;

public class NewsRepository {
    private final NewsApiService apiService;
    private final Context context;

    public NewsRepository(Context context) {
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(NewsApiService.class);
    }

    public Call<NewsResponse> getArticlesForConcerns() {
        SharedPreferences prefs = context.getSharedPreferences("ConcernsPrefs", Context.MODE_PRIVATE);
        Set<String> selectedConcerns = prefs.getStringSet("SelectedChips", new HashSet<>());

        Log.d("NewsRepo", "Concerns selected: " + selectedConcerns);

        StringBuilder queryBuilder = new StringBuilder();

        if (selectedConcerns.isEmpty()) {
            queryBuilder.append("\"New York City\" politics");
            Log.d("NewsRepo", "Default query: NYC politics");
        } else {
            queryBuilder.append("(");
            for (String concern : selectedConcerns) {
                if (queryBuilder.length() > 1) queryBuilder.append(" OR ");
                queryBuilder.append("\"").append(concern).append("\"");
            }
            queryBuilder.append(")");

            queryBuilder.append(" (\"New York City\" OR NYC OR politics OR government)");
            Log.d("NewsRepo", "Concern query: " + queryBuilder.toString());
        }

        String query = queryBuilder.toString();
        String apiKey = context.getString(R.string.news_api_key);

        Log.d("NewsRepo", "Final query: " + query);
        return apiService.getArticles(query, apiKey, "en", "relevancy", 20);
    }
}
