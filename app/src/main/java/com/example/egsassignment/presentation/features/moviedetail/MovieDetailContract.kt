package com.example.egsassignment.presentation.features.moviedetail

import androidx.lifecycle.LiveData
import com.example.egsassignment.domain.usecase.RetrieveMovieDetailUseCase
import androidx.lifecycle.ViewModel as BaseViewModel

/**
 * Created by Phillip Truong
 * date 02/03/2024.
 */
interface MovieDetailContract {

    abstract class ViewModel: BaseViewModel() {
        abstract val movie: LiveData<MovieDetailDisplayable>
        abstract val loadMovieDetail: LiveData<Int>

        abstract fun onMovieDetailRetrieved(result: RetrieveMovieDetailUseCase.Result)
        abstract fun onServiceBound()
        abstract fun initialise(movieId: Int)
    }

    data class MovieDetailDisplayable(
        val backDrop: String,
        val title: String,
        val year: String?,
        val releaseDate: String?,
        val genres: String,
        val duration: String?,
        val tagLine: String,
        val overview: String,
    )
}