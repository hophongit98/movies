package com.example.egsassignment.di

import com.example.egsassignment.data.MovieDetailDtoMapper
import com.example.egsassignment.data.MovieDetailDtoMapperImpl
import com.example.egsassignment.domain.usecase.RetrieveMovieDetailUseCase
import com.example.egsassignment.domain.usecase.RetrieveMovieDetailUseCaseImpl
import dagger.Binds
import dagger.Module

/**
 * Created by Phillip Truong
 * date 01/03/2024.
 */
@Module
abstract class RetrieveMovieDetailModule {
    @Binds
    abstract fun bindsMovieDetailDtoMapperImpl(mapper: MovieDetailDtoMapperImpl): MovieDetailDtoMapper

    @Binds
    abstract fun bindsRetrieveMovieDetailUseCase(impl: RetrieveMovieDetailUseCaseImpl): RetrieveMovieDetailUseCase
}