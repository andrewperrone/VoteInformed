package com.example.voteinformed.network

import com.google.gson.annotations.SerializedName

// Defined the data classes for JSON fields from the NYC API
data class LegislationMatter(
    @SerializedName("MatterId") val id: Int,
    @SerializedName("MatterTitle") val title: String?,
    @SerializedName("MatterStatusName") val status: String?,
    @SerializedName("MatterBodyName") val committee: String?, // The committee name
    @SerializedName("MatterIntroDate") val date: String?,
    @SerializedName("MatterWebLink") val webLink: String? //link to legislatures
)