package com.example.egsassignment.presentation.features.moviedetail.di

import com.example.egsassignment.presentation.features.moviedetail.MovieDetailActivity
import dagger.Subcomponent

/**
 * Created by Phillip Truong
 * date 02/03/2024.
 */
@Subcomponent(modules = [MovieDetailModule::class])
interface MovieDetailComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieDetailComponent
    }

    fun inject(movieDetailActivity: MovieDetailActivity)
}