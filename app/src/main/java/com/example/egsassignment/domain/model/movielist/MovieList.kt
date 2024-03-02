package com.example.egsassignment.domain.model.movielist

/**
 * Created by Phillip Truong
 * date 01/03/2024.
 */
data class MovieList(
    val page: Int,
    val results: List<MovieItem>,
    val totalPages: Int,
    val totalResults: Int
)

data class MovieItem(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)