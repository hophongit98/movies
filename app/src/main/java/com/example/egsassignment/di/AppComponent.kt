package com.example.egsassignment.di

import android.content.Context
import com.example.egsassignment.MovieApplication
import com.example.egsassignment.presentation.features.moviedetail.MoveDetailActivity
import com.example.egsassignment.presentation.features.movielist.di.MovieListComponent
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
    fun inject(moveDetailActivity: MoveDetailActivity)

    fun movieListComponent(): MovieListComponent.Factory
}