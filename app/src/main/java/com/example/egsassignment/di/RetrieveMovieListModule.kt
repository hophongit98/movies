package com.example.egsassignment.di

import com.example.egsassignment.data.MovieListDtoMapper
import com.example.egsassignment.data.MovieListDtoMapperImpl
import com.example.egsassignment.domain.usecase.RetrieveMovieListUseCase
import com.example.egsassignment.domain.usecase.RetrieveMovieListUseCaseImpl
import dagger.Binds
import dagger.Module

/**
 * Created by Phillip Truong
 * date 01/03/2024.
 */
@Module
abstract class RetrieveMovieListModule {
    @Binds
    abstract fun bindsMovieListDtoMapperImpl(mapper: MovieListDtoMapperImpl): MovieListDtoMapper

    @Binds
    abstract fun bindsRetrieveMovieListUseCase(impl: RetrieveMovieListUseCaseImpl): RetrieveMovieListUseCase
}