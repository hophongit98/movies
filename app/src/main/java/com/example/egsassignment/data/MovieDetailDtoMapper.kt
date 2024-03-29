package com.example.egsassignment.data

import com.example.egsassignment.data.dto.moviedetail.MovieDetailDTO
import com.example.egsassignment.domain.model.moviedetail.MovieDetail

/**
 * Created by Phillip Truong
 * date 01/03/2024.
 */
interface MovieDetailDtoMapper {
    fun toMovieDetail(movieDetailDTO: MovieDetailDTO): MovieDetail
}