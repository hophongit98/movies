package com.example.egsassignment.data.remote

/**
 * Created by Phillip Truong
 * date 28/02/2024.
 */
interface MovieRemoteDataSource {
    fun retrieveMovieList()
    fun retrieveMovieDetail(movieId: Int)
}