package com.example.egsassignment.presentation.features.movielist.di

import com.example.egsassignment.presentation.features.movielist.MovieListContract
import com.example.egsassignment.presentation.features.movielist.MovieListViewModel
import dagger.Binds
import dagger.Module

/**
 * Created by Phillip Truong
 * date 02/03/2024.
 */
@Module
abstract class MovieListModule {

    @Binds
    abstract fun bindsMovieListViewModel(movieListViewModel: MovieListViewModel): MovieListContract.ViewModel
}