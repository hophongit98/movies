package com.example.egsassignment.data.remote.apiservice

import com.example.egsassignment.data.dto.MovieDetailsDTO
import com.example.egsassignment.data.dto.MovieItemDTO
import com.example.egsassignment.data.dto.MovieListResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Phillip Truong
 * date 01/03/2024.
 */
interface MovieApi {
    @GET("popular")
    fun retrieveMovieList(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieListResponse<List<MovieItemDTO>>>

    @GET("{movie_id}")
    fun retrieveMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US",
    ): Response<MovieDetailsDTO>
}