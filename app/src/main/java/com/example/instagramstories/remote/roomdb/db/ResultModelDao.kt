package com.example.instagramstories.remote.roomdb.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.instagramstories.remote.model.DataModel

@Dao
interface ResultModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResultModel(resultModel: List<DataModel>)

    @Query("SELECT * FROM app_database")
     fun getResponseModelByPage(): List<DataModel>?


}
