package com.example.voteinformed.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface LegistarApiService {

    // This defines the "Get Matters" command
    @GET("v1/nyc/matters")
    fun getMatters(
        @Query("token") token: String,
        @Query("\$filter") filter: String,
        @Query("\$orderby") orderBy: String = "MatterIntroDate desc",
        @Query("\$top") limit: Int = 20
    ): Call<List<LegislationMatter>>

    // This "companion object" lets you create the API service easily from anywhere
    companion object {
        private const val BASE_URL = "https://webapi.legistar.com/"

        fun create(): LegistarApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LegistarApiService::class.java)
        }
    }
}