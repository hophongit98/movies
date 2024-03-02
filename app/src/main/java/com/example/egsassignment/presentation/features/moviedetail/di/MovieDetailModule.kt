package com.example.egsassignment.presentation.features.moviedetail.di

import com.example.egsassignment.presentation.features.moviedetail.MovieDetailContract
import com.example.egsassignment.presentation.features.moviedetail.MovieDetailViewModel
import dagger.Binds
import dagger.Module

/**
 * Created by Phillip Truong
 * date 02/03/2024.
 */
@Module
abstract class MovieDetailModule {

    @Binds
    abstract fun bindsMovieDetailViewModel(movieDetailViewModel: MovieDetailViewModel): MovieDetailContract.ViewModel
}