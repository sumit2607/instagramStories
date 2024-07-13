package com.example.instagramstories.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://your-api-endpoint.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(StoryApi::class.java)

    suspend fun getStories(): List<Story> {
        return api.getStories()
    }
}