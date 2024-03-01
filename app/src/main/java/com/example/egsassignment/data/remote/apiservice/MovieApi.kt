package com.example.egsassignment.data.remote.apiservice

import com.example.egsassignment.data.dto.MovieDetailsDTO
import com.example.egsassignment.data.dto.MovieListDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Phillip Truong
 * date 01/03/2024.
 */
interface MovieApi {
    @GET("popular")
    suspend fun retrieveMovieList(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Flow<MovieListDTO>

    @GET("{movie_id}")
    suspend fun retrieveMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US",
    ): Flow<MovieDetailsDTO>
}