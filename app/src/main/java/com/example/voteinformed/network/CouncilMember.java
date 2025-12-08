package com.example.voteinformed.network;

import com.google.gson.annotations.SerializedName;

public class CouncilMember {


    @SerializedName("name")
    public String name;


    @SerializedName("district")
    public String district;


    @SerializedName("borough")
    public String borough;


    @SerializedName("political_party")
    public String politicalParty;

}