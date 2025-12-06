package com.example.voteinformed.network;

import com.google.gson.annotations.SerializedName;
import java.util.List;

// structure for the NYS API response
public class LegislatorResponse {
    @SerializedName("result")
    public Result result;

    public static class Result {
        @SerializedName("items")
        public List<Member> items;
    }

    public static class Member {
        @SerializedName("memberFullName")
        public String memberFullName;

        @SerializedName("chamber")
        public String chamber; //

        @SerializedName("party")
        public String party; //

        @SerializedName("districtCode")
        public String districtCode;

        @SerializedName("shortDescription")
        public String shortDescription;
    }
}