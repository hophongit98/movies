package com.example.egsassignment.data

import com.example.egsassignment.data.dto.MovieListDTO
import com.example.egsassignment.domain.model.movielist.MovieList

/**
 * Created by Phillip Truong
 * date 01/03/2024.
 */
interface MovieListDtoMapper {
    fun toMovieList(movieListDTO: MovieListDTO): MovieList
}

