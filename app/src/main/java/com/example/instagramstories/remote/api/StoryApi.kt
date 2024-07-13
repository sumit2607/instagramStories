package com.example.instagramstories.remote.api

interface StoryApi {
    @GET("stories")
    suspend fun getStories(): List<Story>
}
