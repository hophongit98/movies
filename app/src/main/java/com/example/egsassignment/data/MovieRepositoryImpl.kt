package com.example.egsassignment.data

import com.example.egsassignment.data.remote.apiservice.MovieApi
import com.example.egsassignment.domain.ApiResult
import com.example.egsassignment.domain.ErrorType
import com.example.egsassignment.domain.model.moviedetail.MovieDetails
import com.example.egsassignment.domain.model.movielist.MovieList
import com.example.egsassignment.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieListDtoMapper: MovieListDtoMapper,
    private val movieDetailDtoMapper: MovieDetailDtoMapper

) : MovieRepository {
    override suspend fun retrieveMovieList(
        page: Int,
        language: String
    ): Flow<ApiResult<MovieList>> {
        val response = movieApi.retrieveMovieList(page = page, language = language)
        return if (response.isSuccessful && response.body() != null) {
            flowOf(ApiResult(data = movieListDtoMapper.toMovieList(response.body()!!)))
        } else {
            flowOf(
                ApiResult(
                    error = ErrorType(
                        httpCode = response.code(),
                        response.errorBody().toString()
                    )
                )
            )
        }
    }

    override suspend fun retrieveMovieDetail(
        movieId: Int,
        language: String
    ): Flow<ApiResult<MovieDetails>> {
        val response = movieApi.retrieveMovieDetails(movieId = movieId, language = language)
        return if (response.isSuccessful && response.body() != null) {
            flowOf(ApiResult(data = movieDetailDtoMapper.toMovieDetail(response.body()!!)))
        } else {
            flowOf(
                ApiResult(
                    error = ErrorType(
                        httpCode = response.code(),
                        response.errorBody().toString()
                    )
                )
            )
        }
    }
}