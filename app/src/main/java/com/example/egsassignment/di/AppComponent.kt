package com.example.egsassignment.di

import android.content.Context
import com.example.egsassignment.MovieApplication
import com.example.egsassignment.presentation.features.moviedetail.MovieDetailActivity
import com.example.egsassignment.presentation.features.moviedetail.MovieDetailContract
import com.example.egsassignment.presentation.features.moviedetail.di.MovieDetailComponent
import com.example.egsassignment.presentation.features.movielist.di.MovieListComponent
import com.example.egsassignment.service.MoviesService
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Phillip Truong
 * date 29/02/2024.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(application: MovieApplication)
    fun inject(moviesService: MoviesService)
    fun movieListComponent(): MovieListComponent.Factory
    fun movieDetailComponent(): MovieDetailComponent.Factory
}