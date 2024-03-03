package com.example.egsassignment.presentation.features.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.egsassignment.domain.model.moviedetail.MovieDetail
import com.example.egsassignment.domain.usecase.RetrieveMovieDetailUseCase
import com.example.egsassignment.utils.DateFormatUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Phillip Truong
 * date 02/03/2024.
 */
class MovieDetailViewModel @Inject constructor() : MovieDetailContract.ViewModel() {

    private val _movie = MutableLiveData<MovieDetailContract.MovieDetailDisplayable>()
    override val movie: LiveData<MovieDetailContract.MovieDetailDisplayable> = _movie

    private val _loadMovieDetail = MutableLiveData<Int>()
    override val loadMovieDetail: LiveData<Int> = _loadMovieDetail

    private var _movieId: Int = 0

    override fun onMovieDetailRetrieved(result: RetrieveMovieDetailUseCase.Result) {
        when (result) {
            is RetrieveMovieDetailUseCase.Result.Error -> {
                // handle error here
            }
            is RetrieveMovieDetailUseCase.Result.Success -> {
                handleMovieDetailRetrievedSuccessfully(result.movieDetail)
            }
        }
    }

    override fun onServiceBound() {
        _loadMovieDetail.value = _movieId
    }

    override fun initialise(movieId: Int) {
        _movieId = movieId
        _loadMovieDetail.value = movieId
    }

    private fun handleMovieDetailRetrievedSuccessfully(movieDetail: MovieDetail) {
        viewModelScope.launch(Dispatchers.Default) {
            _movie.postValue(MovieDetailContract.MovieDetailDisplayable(
                backDrop = movieDetail.backdropPath,
                title = movieDetail.title,
                year = DateFormatUtils.getYear(movieDetail.releaseDate).toString(),
                releaseDate = DateFormatUtils.convertTo(movieDetail.releaseDate,
                    DateFormatUtils.MMDDYYYY),
                genres = movieDetail.genres.joinToString(", ") { it.name },
                duration = formatMinutesToHoursAndMinutes(movieDetail.runtime),
                tagLine = movieDetail.tagline,
                overview = movieDetail.overview
            ))
        }
    }

    private fun formatMinutesToHoursAndMinutes(totalMinutes: Int): String? {
        if (totalMinutes < 0) {
            return null
        }

        val hours = totalMinutes / 60
        val minutes = totalMinutes % 60

        return when {
            hours > 0 && minutes > 0 -> "${hours}h ${minutes}m"
            hours > 0 -> "${hours}h 0m"
            minutes > 0 -> "${minutes}m"
            else -> "0m"
        }
    }
}