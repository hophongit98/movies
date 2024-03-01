package com.example.egsassignment.data.remote

import com.example.egsassignment.data.remote.apiservice.MovieApi
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRemoteDataSource {

    override suspend fun retrieveMovieList() = movieApi.retrieveMovieList()

    override suspend fun retrieveMovieDetail(movieId: Int) = movieApi.retrieveMovieDetails(movieId)
}