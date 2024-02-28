package com.example.egsassignment.domain.repository

/**
 * Created by Phillip Truong
 * date 28/02/2024.
 */
interface MovieRepository {
    fun retrieveMovieList()
    fun retrieveMovieDetail(movieId: Int)
}