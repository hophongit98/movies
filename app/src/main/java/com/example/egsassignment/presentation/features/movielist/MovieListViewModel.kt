package com.example.egsassignment.presentation.features.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.egsassignment.domain.model.movielist.MovieList
import com.example.egsassignment.presentation.features.movielist.MovieListViewHolder.DisplayItem
import javax.inject.Inject

/**
 * Created by Phillip Truong
 * date 02/03/2024.
 */
class MovieListViewModel @Inject constructor() : MovieListContract.ViewModel(), RetrieveMoviesListener {

    private val _movies = MutableLiveData<List<DisplayItem>>()
    override val movies: LiveData<List<DisplayItem>> = _movies

    private val _navigateToMovieDetail = MutableLiveData<Int>()
    override val navigateToMovieDetail: LiveData<Int> = _navigateToMovieDetail

    override fun onMovieItemSelected(movieId: Int) {
        _navigateToMovieDetail.value = movieId
    }

    override fun onMoviesRetrieved(movieList: MovieList) {
        _movies.value =
            listOf(DisplayItem.Header(movieType = MovieListViewHolder.MovieType.POPULAR)) +
                    movieList.results.map { DisplayItem.Movie(movie = it) }
    }
}