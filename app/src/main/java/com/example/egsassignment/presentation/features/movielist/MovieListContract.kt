package com.example.egsassignment.presentation.features.movielist

import androidx.lifecycle.LiveData
import com.example.egsassignment.domain.model.movielist.MovieList
import androidx.lifecycle.ViewModel as BaseViewModel

/**
 * Created by Phillip Truong
 * date 02/03/2024.
 */
interface MovieListContract {

    interface Navigation {
        val navigateToMovieDetail: LiveData<Int>
    }

    abstract class ViewModel : BaseViewModel(), Navigation {
        abstract val movies: LiveData<List<MovieListViewHolder.DisplayItem>>

        abstract fun onMovieItemSelected(movieId: Int)
    }
}