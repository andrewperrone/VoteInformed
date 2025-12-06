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

    public static List<Article> getArticles() {
        List<Article> list = new ArrayList<>();

        list.add(new Article(
                "NYC launches new voter education campaign ahead of 2025 elections",
                "https://demo.voteinformed.org/articles/nyc-voter-education-2025"
        ));

        list.add(new Article(
                "Debate grows over congestion pricing in Manhattan",
                "https://demo.voteinformed.org/articles/congestion-pricing-manhattan"
        ));

        list.add(new Article(
                "City Council advances affordable housing zoning changes",
                "https://demo.voteinformed.org/articles/affordable-housing-zoning"
        ));

        list.add(new Article(
                "State lawmakers propose new climate resilience funding for coastal areas",
                "https://demo.voteinformed.org/articles/climate-resilience-coastal-ny"
        ));

        list.add(new Article(
                "Public safety reforms: balancing police accountability and community trust",
                "https://demo.voteinformed.org/articles/public-safety-reform-ny"
        ));

        list.add(new Article(
                "Subway modernization plan promises faster, more reliable service",
                "https://demo.voteinformed.org/articles/subway-modernization-plan"
        ));

        list.add(new Article(
                "NYC schools pilot mental health support program for students",
                "https://demo.voteinformed.org/articles/nyc-school-mental-health"
        ));

        list.add(new Article(
                "Small business recovery grants extended for another year",
                "https://demo.voteinformed.org/articles/small-business-recovery-grants"
        ));

        list.add(new Article(
                "Rent stabilization debate intensifies as tenants call for stronger protections",
                "https://demo.voteinformed.org/articles/rent-stabilization-debate"
        ));

        list.add(new Article(
                "Early voting locations expand across New York State",
                "https://demo.voteinformed.org/articles/early-voting-expands-ny"
        ));

        return list;
    }


    public static List<Election> getElections() {
        List<Election> list = new ArrayList<>();
        list.add(new Election("[election name]", new Date(), "[location]", "[description]"));
        return list;
    }

    public static List<Issue> getIssues() {
        List<Issue> list = new ArrayList<>();
        list.add(new Issue("[title]", "[description]", "[type]", "[location]"));
        return list;
    }

    private static final byte[] default_image = new byte[1];

    public static List<Politician> getPoliticians()
    {
        List<Politician> list = new ArrayList<>();
        // Governor of New York
        list.add(new Politician(
                "Kathy Hochul",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/4/41/Kathy_Hochul_2022.jpg\n",
                "518-474-8390",
                "Hochul was born in Buffalo, New York. She received a B.A. from Syracuse University in 1980 and a J.D. from Catholic University in 1983. In addition to working as an attorney in private practice, she was an aide to Rep. John LaFalce (D) from 1984 to 1986 and to Sen. Daniel Patrick Moynihan (D) from 1986 to 1988. She served as Deputy Clerk of Erie County in New York from 2003 to 2007 and as Clerk of Erie County from 2007 to 2011.",
                "The Honorable Kathy Hochul\nGovernor of New York State\nNYS State Capitol Building\nAlbany, NY 12224"
        ));

        // Mayor of New York City
        list.add(new Politician(
                "Eric Adams",
                "Democratic",
                "https://en.wikipedia.org/wiki/File:Eric_Adams_at_City_Hall_2023_(3x4_cropped).jpg",
                "Email: mayoreric@cityhall.nyc.gov",
                "Eric Adams graduated from Bayside High School. Adams earned a master's degree in public administration from Marist College and degrees from New York City Technical College and the John Jay College of Criminal Justice. His career experience includes working as a captain with the New York Police Department. Adams founded 100 Blacks in Law Enforcement Who Care",
                "Mayor Eric Adams\nCity Hall\nNew York, NY 10007"
        ));

        // US Senator Chuck Schumer
        list.add(new Politician(
                "Chuck Schumer",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/6/6b/Chuck_Schumer_official_photo_%283x4_cropped%29.jpg",
                "DC Office Phone: (202) 224-6542 \n Official Website Contact: https://www.schumer.senate.gov/contact/email-chuck",
                "Charles Ellis Schumer (born 1950) is the senior U.S. Senator from New York, a seat he has held since 1999. He currently serves as the Senate Majority Leader. He previously served in the U.S. House of Representatives and the New York State Assembly.",
                "U.S. Senator for New York \n D.C. Address: 322 Hart Senate Office Building, Washington, DC 20510"
        ));

        // US Senator Kirsten Gillibrand
        list.add(new Politician(
                "Kirsten Gillibrand",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/9/9a/Kirsten_Gillibrand%2C_official_photo%2C_116th_Congress.jpg",
                "DC Office Phone: (202) 224-4451 \n Official Website Contact: https://www.gillibrand.senate.gov/contact",
                "Kirsten Elizabeth Gillibrand (born 1966) is the junior U.S. Senator from New York, serving since 2009. She previously represented New York's 20th congressional district in the House. She is known for her advocacy on military justice and sexual assault reforms.",
                "U.S. Senator for New York \n D.C. Address: 478 Russell Senate Office Building, Washington, DC 20510"
        ));
        return list;
    }

    public static List<User> getUsers()
    {
        List<User> list = new ArrayList<>();
        list.add(new User("admin", "admin@voteinformed.com", "voteinformedadmin", "NYC", "[admin preferences]", true ));
        list.add(new User("Andrew", "aperro01@nyit.edu", "perrone", "NYC", "[admin preferences]", true ));
        list.add(new User("[name]", "[email]", "[password]", "[location]", "[preferences]"));
        return list;
    }


}
