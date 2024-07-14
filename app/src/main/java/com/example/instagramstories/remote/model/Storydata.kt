package com.example.instagramstories.remote.model

import android.os.Parcel
import android.os.Parcelable

data class Storydata(
    val story_photo: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(story_photo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Storydata> {
        override fun createFromParcel(parcel: Parcel): Storydata {
            return Storydata(parcel)
        }

        override fun newArray(size: Int): Array<Storydata?> {
            return arrayOfNulls(size)
        }
    }
}
