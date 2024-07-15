package com.example.instagramstories.remote.model

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


data class DataModel(
    val video_url: String,
    val image_url: String,
    val user_name: String,
    val storydata: List<Storydata>? = null
) : Parcelable

class SharedViewModel : ViewModel() {

    // LiveData to hold the list of stories
    private val _storyList = MutableLiveData<List<Storydata>>()
    val storyList: LiveData<List<Storydata>> get() = _storyList


    // Method to update the story list
    fun setStoryList(dataModels: List<Storydata>) {
        _storyList.value = dataModels
    }


}
