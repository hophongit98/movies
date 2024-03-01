package com.example.egsassignment.domain.model.movielist

/**
 * Created by Phillip Truong
 * date 01/03/2024.
 */
class MovieList(
    page: Int,
    results: List<MovieItem>,
    totalPages: Int,
    totalResults: Int
)

class MovieItem(
    adult: Boolean,
    backdropPath: String,
    genreIds: List<Int>,
    id: Int,
    originalLanguage: String,
    originalTitle: String,
    overview: String,
    popularity: Double,
    posterPath: String,
    releaseDate: String,
    title: String,
    video: Boolean,
    voteAverage: Double,
    voteCount: Int
)