package com.example.egsassignment.data

import com.example.egsassignment.data.dto.moviedetail.*
import com.example.egsassignment.domain.model.moviedetail.*
import javax.inject.Inject

class MovieDetailDtoMapperImpl @Inject constructor() : MovieDetailDtoMapper {
    override fun toMovieDetail(movieDetailsDTO: MovieDetailsDTO): MovieDetail {
        return MovieDetail(
            adult = movieDetailsDTO.adult,
            backdropPath = movieDetailsDTO.backdropPath,
            belongsToCollection = movieDetailsDTO.belongsToCollection,
            budget = movieDetailsDTO.budget,
            genres = movieDetailsDTO.genres.toGenre(),
            homepage = movieDetailsDTO.homepage,
            id = movieDetailsDTO.id,
            imdbId = movieDetailsDTO.imdbId,
            originalLanguage = movieDetailsDTO.originalLanguage,
            originalTitle = movieDetailsDTO.originalTitle,
            overview = movieDetailsDTO.overview,
            popularity = movieDetailsDTO.popularity,
            posterPath = movieDetailsDTO.posterPath,
            productionCompanies = movieDetailsDTO.productionCompanies.toProductionCompany(),
            productionCountries = movieDetailsDTO.productionCountries.toProductionCountry(),
            releaseDate = movieDetailsDTO.releaseDate,
            revenue = movieDetailsDTO.revenue,
            runtime = movieDetailsDTO.runtime,
            spokenLanguages = movieDetailsDTO.spokenLanguages.toSpokenLanguage(),
            status = movieDetailsDTO.status,
            tagline = movieDetailsDTO.tagline,
            title = movieDetailsDTO.title,
            video = movieDetailsDTO.video,
            voteCount = movieDetailsDTO.voteCount,
            voteAverage = movieDetailsDTO.voteAverage
        )
    }

    private fun List<GenreDTO>.toGenre(): List<Genre> {
        return this.map {
            Genre(
                id = it.id,
                name = it.name
            )
        }
    }

    private fun List<ProductionCompanyDTO>.toProductionCompany(): List<ProductionCompany> {
        return this.map {
            ProductionCompany(
                id = it.id,
                logoPath = it.logoPath,
                name = it.name,
                originCountry = it.originCountry
            )
        }
    }

    private fun List<ProductionCountryDTO>.toProductionCountry(): List<ProductionCountry> {
        return this.map {
            ProductionCountry(
                iso31661 = it.iso31661,
                name = it.name
            )
        }
    }

    private fun List<SpokenLanguageDTO>.toSpokenLanguage(): List<SpokenLanguage> {
        return this.map {
            SpokenLanguage(
                englishName = it.englishName,
                iso6391 = it.iso6391,
                name = it.name
            )
        }
    }
}