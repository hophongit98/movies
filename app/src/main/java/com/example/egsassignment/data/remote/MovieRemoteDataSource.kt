package com.example.egsassignment.data.remote

import com.example.egsassignment.data.dto.MovieDetailsDTO
import com.example.egsassignment.data.dto.MovieListDTO
import kotlinx.coroutines.flow.Flow

/**
 * Created by Phillip Truong
 * date 28/02/2024.
 */
interface MovieRemoteDataSource {
    suspend fun retrieveMovieList(): Flow<MovieListDTO>
    suspend fun retrieveMovieDetail(movieId: Int): Flow<MovieDetailsDTO>
}