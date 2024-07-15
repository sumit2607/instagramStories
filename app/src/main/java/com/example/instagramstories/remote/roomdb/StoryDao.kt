package com.example.instagramstories.remote.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StoryDao {

    @Query("SELECT * FROM story_table")
    fun getAllStories(): LiveData<List<MyDataCLass>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stories: List<MyDataCLass>)
}
