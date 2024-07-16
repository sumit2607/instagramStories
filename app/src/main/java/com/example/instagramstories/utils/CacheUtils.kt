package com.example.instagramstories.utils

import android.content.Context
import com.google.android.exoplayer2.database.DatabaseProvider
import com.google.android.exoplayer2.database.ExoDatabaseProvider
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.cache.CacheDataSink
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import java.io.File

object CacheUtils {

    private var simpleCache: SimpleCache? = null

    fun getSimpleCache(context: Context): SimpleCache {
        if (simpleCache == null) {
            val cacheFolder = File(context.cacheDir, "media")
            val cacheSize = 100 * 1024 * 1024L // 100MB
            val cacheEvictor = LeastRecentlyUsedCacheEvictor(cacheSize)
            val databaseProvider: DatabaseProvider = ExoDatabaseProvider(context)
            simpleCache = SimpleCache(cacheFolder, cacheEvictor, databaseProvider)
        }
        return simpleCache!!
    }

    fun buildCacheDataSourceFactory(context: Context): DataSource.Factory {
        val cache = getSimpleCache(context)
        val httpDataSourceFactory = DefaultHttpDataSource.Factory()
        val defaultDataSourceFactory = DefaultDataSource.Factory(context, httpDataSourceFactory)

        return CacheDataSource.Factory()
            .setCache(cache)
            .setUpstreamDataSourceFactory(defaultDataSourceFactory)
            .setCacheWriteDataSinkFactory(CacheDataSink.Factory().setCache(cache))
            .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)
    }
}
