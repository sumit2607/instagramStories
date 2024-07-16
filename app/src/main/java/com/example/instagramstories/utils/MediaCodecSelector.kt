package com.example.instagramstories.utils

import android.content.Context
import android.media.MediaCodecInfo
import android.media.MediaCodecList
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

object MediaCodecSelector {
    fun selectDecoder(context: Context, mimeType: String): MediaCodecInfo? {
        val codecList = MediaCodecList(MediaCodecList.ALL_CODECS)
        for (codec in codecList.codecInfos) {
            if (!codec.isEncoder) {
                for (type in codec.supportedTypes) {
                    if (type.equals(mimeType, ignoreCase = true)) {
                        if (codec.isEncoder) continue
                        if (codec.name.contains("omx.google.") || codec.name.contains("omx.qcom.")) {
                            return codec
                        }
                    }
                }
            }
        }
        return null
    }

    fun fallbackToSoftware(context: Context, mediaItem: MediaItem): MediaSource {
        val dataSourceFactory =
            DefaultDataSourceFactory(context, Util.getUserAgent(context, "your-app-name"))
        return ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(mediaItem)
    }
}
