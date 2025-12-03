package com.example.voteinformed;

import android.content.Context;
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

    public Call<NewsResponse> getArticlesForConcern(String concern) {
        // Added "politics" to get more relevant political news about the concern
        String query = "\"" + concern + "\" AND \"New York City\" AND politics";
        String apiKey = context.getString(R.string.news_api_key);
        return apiService.getArticles(query, apiKey, "en", "popularity", 10);
    }
}
