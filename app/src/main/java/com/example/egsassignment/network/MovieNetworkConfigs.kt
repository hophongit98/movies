package com.example.egsassignment.network

import android.content.Context
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * Created by Phillip Truong
 * date 01/03/2024.
 */
interface MovieNetworkConfigs {
    val cacheSize: Long
    val timeOut: Long
    val baseUrl: String
    val httpLoggingInterceptor: HttpLoggingInterceptor
    val okHttpClient: OkHttpClient
}

class MovieNetworkConfigsImpl(context: Context) : MovieNetworkConfigs {
    override val cacheSize = 10L * 1024 * 1024
    override val timeOut = 30L
    override val baseUrl = "https://api.themoviedb.org/3/movie/"

    override val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY
    )

    override val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .readTimeout(timeOut, TimeUnit.SECONDS)
        .writeTimeout(timeOut, TimeUnit.SECONDS)
        .cache(Cache(context.cacheDir, cacheSize))
        .build()
}