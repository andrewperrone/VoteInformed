package com.example.voteinformed.data.util;

import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.Election;
import com.example.voteinformed.data.entity.Issue;
import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InitialData {

    public static List<Article> getArticles()
    {
        List<Article> list = new ArrayList<>();
        list.add(new Article("title","[url]"));
        return list;
    }

    public static List<Election> getElections()
    {
        List<Election> list = new ArrayList<>();
        list.add(new Election("[election name]", new Date(), "[location]", "[description]"));
        return list;
    }

    public static List<Issue> getIssues()
    {
        List<Issue> list = new ArrayList<>();
        list.add(new Issue("[title]", "[description]", "[type]", "[location]"));
        return list;
    }

    private static final byte[] default_image = new byte[1];

    public static List<Politician> getPoliticians()
    {
        List<Politician> list = new ArrayList<>();
        list.add(new Politician("John Doe", "Independent", default_image, "[contact info]", "[Background]", "[Location]"));
        list.add(new Politician("Jane Smith", "Democratic", default_image, "[contact info]", "[Background]", "[Location]"));
        list.add(new Politician("Alice Johnson", "Republican", default_image, "[contact info]", "[Background]", "[Location]"));
        return list;
    }

    public static List<User> getUsers()
    {
        List<User> list = new ArrayList<>();
        list.add(new User("[name]", "[email]", "[password]", "[location]", "[preferences]"));
        return list;
    }


}
