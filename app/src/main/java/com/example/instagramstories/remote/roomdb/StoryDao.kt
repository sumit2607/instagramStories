package com.example.instagramstories.remote.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.instagramstories.remote.model.DataModel

@Dao
interface StoryDao {

    @Query("SELECT * FROM story_table")
    fun getAllStories(): LiveData<List<DataModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stories: List<DataModel>)
}
