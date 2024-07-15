package com.example.instagramstories.remote.model

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.parcel.Parcelize


data class DataModel(
    val video_url: String,
    val image_url: String,
    val user_name: String? = null,
    val storydata: List<Storydata>? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString(),
        TODO("storydata")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(video_url)
        parcel.writeString(image_url)
        parcel.writeString(user_name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataModel> {
        override fun createFromParcel(parcel: Parcel): DataModel {
            return DataModel(parcel)
        }

        override fun newArray(size: Int): Array<DataModel?> {
            return arrayOfNulls(size)
        }
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
