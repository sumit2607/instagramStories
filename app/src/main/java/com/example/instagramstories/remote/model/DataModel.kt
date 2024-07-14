package com.example.instagramstories.remote.model

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


data class DataModel(
    val video_url: String,
    val image_url: String,
    val storydata: List<Storydata>? = null
) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        TODO("Not yet implemented")
    }
}

class SharedViewModel : ViewModel() {

    // LiveData to hold the list of stories
    private val _storyList = MutableLiveData<List<Storydata>>()
    val storyList: LiveData<List<Storydata>> get() = _storyList



    // Method to update the story list
    fun setStoryList(dataModels: List<Storydata>) {
        _storyList.value = dataModels
    }




}
