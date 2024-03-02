package com.example.egsassignment.presentation.features.movielist

import com.example.egsassignment.domain.model.movielist.MovieList

/**
 * Created by Phillip Truong
 * date 02/03/2024.
 */
interface RetrieveMoviesListener {
    fun onMoviesRetrieved(movieList: MovieList)
}