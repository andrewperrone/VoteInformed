package com.example.voteinformed;

import com.example.voteinformed.Article;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {
    @SerializedName("articles")
    public List<Article> articles;

    @SerializedName("status")
    public String status;

    @SerializedName("totalResults")
    public int totalResults;
}
