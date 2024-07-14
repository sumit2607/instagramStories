package com.example.instagramstories.remote.api

import com.example.instagramstories.remote.model.DataModel
import retrofit2.Call
import retrofit2.http.GET

interface StoryApi {
    @GET("VideoDummyApi/dummy_data.json")
    fun getItems(): Call<List<DataModel>>
}
