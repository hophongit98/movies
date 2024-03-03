package com.example.egsassignment.presentation.features.movielist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.egsassignment.domain.model.movielist.MovieList
import com.example.egsassignment.domain.usecase.RetrieveMovieListUseCase
import com.example.egsassignment.presentation.features.movielist.MovieListViewHolder.DisplayItem
import javax.inject.Inject

/**
 * Created by Phillip Truong
 * date 02/03/2024.
 */
class MovieListViewModel @Inject constructor() : MovieListContract.ViewModel() {

    private val _movies = MutableLiveData<List<DisplayItem>>()
    override val movies: LiveData<List<DisplayItem>> = _movies

    private val _navigateToMovieDetail = MutableLiveData<Int>()
    override val navigateToMovieDetail: LiveData<Int> = _navigateToMovieDetail

    private val _loadPage = MutableLiveData<Int>()
    override val loadPage: LiveData<Int> = _loadPage
    private var currentPage = 1

    private val displayItems = mutableListOf<DisplayItem>(DisplayItem.Header(movieType = MovieListViewHolder.MovieType.POPULAR))

    override fun onServiceBound() {
        Log.d("Phillip", "onServiceBound - load page=$currentPage")
        _loadPage.value = currentPage
    }

    override fun onMovieItemSelected(movieId: Int) {
        _navigateToMovieDetail.value = movieId
    }

    override fun onMoviesRetrieved(result: RetrieveMovieListUseCase.Result) {
        when (result) {
            is RetrieveMovieListUseCase.Result.Error -> {
                // handle error
            }
            is RetrieveMovieListUseCase.Result.Success -> {
                handleMoviesRetrievedSuccessfully(result.movieList)
            }
        }
    }

    override fun loadNextPage() {
        _loadPage.value = ++currentPage
    }

    private fun handleMoviesRetrievedSuccessfully(movieList: MovieList) {
        displayItems.addAll(movieList.results.map { DisplayItem.Movie(movie = it) })
        _movies.value = displayItems
    }
}