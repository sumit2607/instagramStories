package com.example.instagramstories.repo

import androidx.lifecycle.LiveData
import com.example.instagramstories.remote.api.StoryApi
import com.example.instagramstories.remote.model.DataModel
import com.example.instagramstories.remote.model.Storydata
import com.example.instagramstories.remote.roomdb.StoryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StoryRepository(
    private val storyDao: StoryDao,
    private val storyApiService: StoryApi // Assume you have an API service class
) {

    val allStories: LiveData<List<DataModel>> = storyDao.getAllStories()

    // Fetch stories from API and save to database
    suspend fun fetchAndSaveStories() {
        withContext(Dispatchers.IO) {
            try {
                val response = storyApiService.getItems()// API call
                if (response.isExecuted) {
                    response.execute().body()?.let { stories ->
                        //  storyDao.deleteAll() // Clear the old data
                        for (i in stories.listIterator()) {
                            storyDao.insert(stories) // Insert new data
                        }

                    }
                }
            } catch (e: Exception) {
                // Handle exceptions
                e.printStackTrace()
            }
        }
    }



//    // Delete all stories
//    suspend fun deleteAll() {
//        storyDao.deleteAll()
//    }
}
