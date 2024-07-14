package com.example.instagramstories.remote.model

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataModel(
    val video_url: String,
    val image_url: String
) : Parcelable

class SharedViewModel : ViewModel() {

    // LiveData to hold the list of stories
    private val _storyList = MutableLiveData<List<DataModel>>()
    val storyList: LiveData<List<DataModel>> get() = _storyList

    // Method to update the story list
    fun setStoryList(dataModels: List<DataModel>) {
        _storyList.value = dataModels
    }
}
