package com.example.instagramstories.repo

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.instagramstories.remote.api.RetrofitClient
import com.example.instagramstories.remote.api.StoryApi
import com.example.instagramstories.remote.db.AppDatabase
import com.example.instagramstories.remote.model.DataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StoryRepository private constructor(private val context: Context) {

    private val apiService: StoryApi = RetrofitClient.getInstance().create(StoryApi::class.java)

    // Initialize the database
    private val database: AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "app_database"
    ).build()
    private val videoLiveData = MutableLiveData<List<DataModel>>()

    val video: LiveData<List<DataModel>>
        get() = videoLiveData

    suspend fun getMemes() {
        withContext(Dispatchers.IO) {
            if (isInternetAvailable()) {
                val result = apiService.getItems()
                val response = result.execute().body()
                response?.let {
                    database.resultModelDao().insertResultModel(it)
                    videoLiveData.postValue(it)

                }
            } else {
                val cachedData = database.resultModelDao().getResponseModelByPage()
                cachedData?.let {
                    videoLiveData.postValue(it)
                }
            }
        }
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    companion object {
        @Volatile
        private var INSTANCE: StoryRepository? = null

        fun getInstance(context: Context): StoryRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = StoryRepository(context)
                INSTANCE = instance
                instance
            }
        }
    }
}



