package com.example.egsassignment.di

import com.example.egsassignment.data.remote.apiservice.MovieApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Phillip Truong
 * date 01/03/2024.
 */
@Module(includes = [NetWorkModule::class])
class ApiModule {

    @Provides
    fun providesMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }
}