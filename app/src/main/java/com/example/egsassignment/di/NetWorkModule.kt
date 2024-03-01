package com.example.egsassignment.di

import android.content.Context
import com.example.egsassignment.network.MovieNetworkConfigs
import com.example.egsassignment.network.MovieNetworkConfigsImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Phillip Truong
 * date 01/03/2024.
 */
@Module
class NetWorkModule {

    @Provides
    fun provideMovieNetworkConfigs(context: Context): MovieNetworkConfigs = MovieNetworkConfigsImpl(context)

    @Provides
    fun provideRetrofit(networkConfigs: MovieNetworkConfigs): Retrofit = Retrofit.Builder()
        .baseUrl(networkConfigs.baseUrl)
        .client(networkConfigs.okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}