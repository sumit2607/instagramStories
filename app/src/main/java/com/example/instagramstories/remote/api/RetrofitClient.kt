package com.example.instagramstories.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // private var BASE_URL = "https://raw.githubusercontent.com/13yadav/API/main/"
    private var BASE_URL = "https://raw.githubusercontent.com/sumit2607/API/master/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

}