package com.example.egsassignment.data

import com.example.egsassignment.data.dto.movielist.MovieItemDTO
import com.example.egsassignment.data.dto.movielist.MovieListResponse
import com.example.egsassignment.domain.model.movielist.MovieList

/**
 * Created by Phillip Truong
 * date 01/03/2024.
 */
interface MovieListDtoMapper {
    fun toMovieList(movieListDTO: MovieListResponse<List<MovieItemDTO>>): MovieList
}

