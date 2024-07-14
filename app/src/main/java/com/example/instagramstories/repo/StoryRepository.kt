package com.example.instagramstories.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.instagramstories.remote.api.StoryApi
import com.example.instagramstories.remote.model.DataModel

class StoryRepository(private val apiService: StoryApi) {
    private val videoLiveData = MutableLiveData<List<DataModel>>()
    val video: LiveData<List<DataModel>>
        get() = videoLiveData

    suspend fun getMemes() {
        val result = apiService.getItems()
        if (result != null) {
            videoLiveData.postValue(result.execute().body())
        }
    }
}

