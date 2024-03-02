package com.example.egsassignment.presentation.features.movielist.di

import com.example.egsassignment.presentation.features.movielist.MovieListActivity
import dagger.Subcomponent

/**
 * Created by Phillip Truong
 * date 02/03/2024.
 */
@Subcomponent(modules = [MovieListModule::class])
interface MovieListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieListComponent
    }

    fun inject(movieListActivity: MovieListActivity)
}