package com.example.egsassignment.domain.repository

import com.example.egsassignment.domain.ApiResult
import com.example.egsassignment.domain.model.moviedetail.MovieDetail
import com.example.egsassignment.domain.model.movielist.MovieList
import kotlinx.coroutines.flow.Flow

/**
 * Created by Phillip Truong
 * date 28/02/2024.
 */
interface MovieRepository {
    fun retrieveMovieList(page: Int = 1, language: String = "en-US"): Flow<ApiResult<MovieList>>
    fun retrieveMovieDetail(movieId: Int, language: String = "en-US"): Flow<ApiResult<MovieDetail>>
}