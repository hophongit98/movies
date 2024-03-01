package com.example.egsassignment.data

import com.example.egsassignment.data.dto.MovieItemDTO
import com.example.egsassignment.data.dto.MovieListResponse
import com.example.egsassignment.domain.model.movielist.MovieItem
import com.example.egsassignment.domain.model.movielist.MovieList

class MovieListDtoMapperImpl : MovieListDtoMapper {
    override fun toMovieList(movieListDTO: MovieListResponse<List<MovieItemDTO>>): MovieList {
        return MovieList(
            page = movieListDTO.page,
            results = movieListDTO.results.toMovieItem(),
            totalPages = movieListDTO.totalPages,
            totalResults = movieListDTO.totalResults
        )
    }

    private fun List<MovieItemDTO>.toMovieItem(): List<MovieItem> {
        return this.map {
            MovieItem(
                adult = it.adult,
                backdropPath = it.backdropPath,
                genreIds = it.genreIds,
                id = it.id,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                video = it.video,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount
            )
        }
    }
}