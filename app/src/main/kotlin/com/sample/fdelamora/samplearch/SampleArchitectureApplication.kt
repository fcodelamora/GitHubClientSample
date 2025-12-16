package com.sample.fdelamora.samplearch

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import dagger.hilt.android.HiltAndroidApp
import okhttp3.Dispatcher
import okhttp3.OkHttpClient

@HiltAndroidApp
open class SampleArchitectureApplication :
    Application(),
    ImageLoaderFactory {

    // Set maxRequest to prevent connection issues to the image server due to overload
    override fun newImageLoader(): ImageLoader {
        val imageCacheDir = "image_cache"

        val okHttpDispatcher = Dispatcher().apply { maxRequests = 3 }

        return ImageLoader.Builder(this)
            .respectCacheHeaders(false)
            .crossfade(true)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.5)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(this.cacheDir.resolve(imageCacheDir))
                    .build()
            }
            .okHttpClient {
                OkHttpClient.Builder()
                    .dispatcher(okHttpDispatcher)
                    .build()
            }
            .build()
    }
}
