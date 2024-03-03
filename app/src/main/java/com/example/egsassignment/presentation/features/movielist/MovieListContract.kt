package com.example.egsassignment.presentation.features.movielist

import androidx.lifecycle.LiveData
import com.example.egsassignment.domain.model.movielist.MovieList
import com.example.egsassignment.domain.usecase.RetrieveMovieListUseCase
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
        abstract val loadPage: LiveData<Int>

        abstract fun onMovieItemSelected(movieId: Int)
        abstract fun onMoviesRetrieved(result: RetrieveMovieListUseCase.Result)
        abstract fun loadNextPage()
        abstract fun onServiceBound()
    }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }
}