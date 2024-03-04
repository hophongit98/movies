package com.example.egsassignment.data.remote.api

import com.example.egsassignment.data.dto.moviedetail.MovieDetailDTO
import com.example.egsassignment.data.dto.movielist.MovieItemDTO
import com.example.egsassignment.data.dto.movielist.MovieListResponse
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Phillip Truong
 * date 01/03/2024.
 */
interface MovieApi {
    @GET("popular")
    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NDc4N2RjMTEzYzZkNDA0ZGU3MmZkZWI3OGM0MjBjOCIsInN1YiI6IjY1ZGY0ZTE0YTliOWE0MDE4NjhmMmJlMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Wd4JCp3EisrZkv-lVDlyxQlR0JIH0__nM5WrHAsCF-I",
        "Accept: application/json",
    )
    suspend fun retrieveMovieList(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieListResponse<List<MovieItemDTO>>>

    @GET("{movie_id}")
    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NDc4N2RjMTEzYzZkNDA0ZGU3MmZkZWI3OGM0MjBjOCIsInN1YiI6IjY1ZGY0ZTE0YTliOWE0MDE4NjhmMmJlMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Wd4JCp3EisrZkv-lVDlyxQlR0JIH0__nM5WrHAsCF-I",
        "Accept: application/json",
    )
    suspend fun retrieveMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US",
    ): Response<MovieDetailDTO>
}