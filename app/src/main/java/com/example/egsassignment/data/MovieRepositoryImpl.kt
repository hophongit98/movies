package com.example.egsassignment.data

import com.example.egsassignment.data.remote.apiservice.MovieApi
import com.example.egsassignment.domain.ApiResult
import com.example.egsassignment.domain.ErrorType
import com.example.egsassignment.domain.model.moviedetail.MovieDetails
import com.example.egsassignment.domain.model.movielist.MovieList
import com.example.egsassignment.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieListDtoMapper: MovieListDtoMapper,
    private val movieDetailDtoMapper: MovieDetailDtoMapper

) : MovieRepository {
    override fun retrieveMovieList(
        page: Int,
        language: String
    ): Flow<ApiResult<MovieList>> {
        return flow {
            val response = movieApi.retrieveMovieList(page = page, language = language)
            if (response.isSuccessful && response.body() != null) {
                ApiResult(data = movieListDtoMapper.toMovieList(response.body()!!))
            } else {
                ApiResult(
                    error = ErrorType(
                        httpCode = response.code(),
                        response.errorBody().toString()
                    )
                )
            }
        }
    }

    override fun retrieveMovieDetail(
        movieId: Int,
        language: String
    ): Flow<ApiResult<MovieDetails>> {
        return flow {
            val response = movieApi.retrieveMovieDetails(movieId = movieId, language = language)
            if (response.isSuccessful && response.body() != null) {
                ApiResult(data = movieDetailDtoMapper.toMovieDetail(response.body()!!))
            } else {
                ApiResult(
                    error = ErrorType(
                        httpCode = response.code(),
                        response.errorBody().toString()
                    )
                )
            }
        }
    }
}