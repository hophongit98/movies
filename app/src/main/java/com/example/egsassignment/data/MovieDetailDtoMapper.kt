package com.example.egsassignment.data

import com.example.egsassignment.data.dto.MovieDetailsDTO
import com.example.egsassignment.domain.model.moviedetail.MovieDetails

/**
 * Created by Phillip Truong
 * date 01/03/2024.
 */
interface MovieDetailDtoMapper {
    fun toMovieDetail(movieDetailsDTO: MovieDetailsDTO): MovieDetails
}