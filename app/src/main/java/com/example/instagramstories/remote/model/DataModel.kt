package com.example.instagramstories.remote.model


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.instagramstories.remote.roomdb.StoryDataTypeConverter

@Entity(tableName = "story_table")
@TypeConverters(StoryDataTypeConverter::class)
data class DataModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val video_url: String,
    val image_url: String,
    val storydata: List<Storydata>? = null
) : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(video_url)
        dest.writeString(image_url)
        dest.writeTypedList(storydata)
    }

    companion object CREATOR : Parcelable.Creator<DataModel> {
        override fun createFromParcel(parcel: Parcel): DataModel {
            return DataModel(
                parcel.readInt(),
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.createTypedArrayList(Storydata.CREATOR)
            )
        }

        override fun newArray(size: Int): Array<DataModel?> {
            return arrayOfNulls(size)
        }
    }
}
