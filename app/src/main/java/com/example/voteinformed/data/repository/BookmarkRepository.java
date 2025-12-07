package com.example.voteinformed.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.voteinformed.Article;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BookmarkRepository {
    private static final String PREFS_NAME = "BookmarksPrefs";
    private static final String BOOKMARKED_ARTICLES_KEY = "BookmarkedArticles";
    private final SharedPreferences prefs;
    private final Gson gson = new Gson();

    public BookmarkRepository(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public boolean isBookmarked(String url) {
        List<Article> articles = getBookmarkedArticles();
        return articles.stream().anyMatch(a -> a.url != null && a.url.equals(url));
    }

    public void addBookmark(Article article) {
        List<Article> articles = getBookmarkedArticles();
        articles.removeIf(a -> a.url != null && a.url.equals(article.url));
        articles.add(article);
        saveArticles(articles);
    }

    public void removeBookmark(String url) {
        List<Article> articles = getBookmarkedArticles();
        articles.removeIf(a -> a.url != null && a.url.equals(url));
        saveArticles(articles);
    }

    public void toggleBookmark(Article article) {
        if (isBookmarked(article.url)) {
            removeBookmark(article.url);
        } else {
            addBookmark(article);
        }
    }

    public List<Article> getBookmarkedArticles() {
        String json = prefs.getString(BOOKMARKED_ARTICLES_KEY, "[]");
        Type type = new TypeToken<List<Article>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    private void saveArticles(List<Article> articles) {
        String json = gson.toJson(articles);
        prefs.edit().putString(BOOKMARKED_ARTICLES_KEY, json).apply();
    }
}
