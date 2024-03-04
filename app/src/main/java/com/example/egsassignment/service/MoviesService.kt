package com.example.egsassignment.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.egsassignment.MovieApplication
import com.example.egsassignment.domain.usecase.RetrieveMovieDetailUseCase
import com.example.egsassignment.domain.usecase.RetrieveMovieListUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Phillip Truong
 * date 28/02/2024.
 */
class MoviesService: Service() {

    @Inject
    lateinit var retrieveMovieListUseCase: RetrieveMovieListUseCase

    @Inject
    lateinit var retrieveMovieDetailUseCase: RetrieveMovieDetailUseCase

    private val binder = MoviesBinder()

    inner class MoviesBinder : Binder() {
        fun getService(): MoviesService = this@MoviesService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onCreate() {
        (application as MovieApplication).appComponent.inject(this)
        super.onCreate()
    }

    fun retrieveMovieList(page: Int): Flow<RetrieveMovieListUseCase.Result> {
        return retrieveMovieListUseCase.execute(RetrieveMovieListUseCase.Input(page = page))
    }

    fun retrieveMovieDetails(movieId: Int): Flow<RetrieveMovieDetailUseCase.Result> {
        return retrieveMovieDetailUseCase.execute(RetrieveMovieDetailUseCase.Input(movieId = movieId))
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cleanup code, if needed
    }
}