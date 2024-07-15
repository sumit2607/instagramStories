package com.example.instagramstories.remote.roomdb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.instagramstories.remote.model.DataModel


@Database(entities = [DataModel::class], version = 1)
@TypeConverters(Converters::class) // Register TypeConverters
abstract class AppDatabase : RoomDatabase() {

    abstract fun resultModelDao(): ResultModelDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null


        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}