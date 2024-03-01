package com.example.egsassignment.domain.model.moviedetail

/**
 * Created by Phillip Truong
 * date 01/03/2024.
 */

class MovieDetails(
    adult: Boolean,
    backdropPath: String,
    belongsToCollection: String?,
    budget: Long,
    genres: List<Genre>,
    homepage: String,
    id: Int,
    imdbId: String,
    originalLanguage: String,
    originalTitle: String,
    overview: String,
    popularity: Double,
    posterPath: String,
    productionCompanies: List<ProductionCompany>,
    productionCountries: List<ProductionCountry>,
    releaseDate: String,
    revenue: Long,
    runtime: Int,
    spokenLanguages: List<SpokenLanguage>,
    status: String,
    tagline: String,
    title: String,
    video: Boolean,
    voteAverage: Double,
    voteCount: Int
)

class Genre(
    id: Int,
    name: String
)

class ProductionCompany(
    id: Int,
    logoPath: String?,
    name: String,
    originCountry: String
)

class ProductionCountry(
    iso31661: String,
    name: String
)

class SpokenLanguage(
    englishName: String,
    iso6391: String,
    name: String
)
