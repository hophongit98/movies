package com.example.egsassignment.di

import com.example.egsassignment.data.MovieRepositoryImpl
import com.example.egsassignment.domain.repository.MovieRepository
import com.example.egsassignment.domain.usecase.RetrieveMovieDetailUseCase
import com.example.egsassignment.domain.usecase.RetrieveMovieListUseCase
import com.example.egsassignment.service.MoviesService
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by Phillip Truong
 * date 29/02/2024.
 */
@Module(
    includes = [
        NetWorkModule::class,
        ApiModule::class,
        RetrieveMovieDetailModule::class,
        RetrieveMovieListModule::class,]
)
abstract class AppModule {

    @Binds
    abstract fun bindsMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

}