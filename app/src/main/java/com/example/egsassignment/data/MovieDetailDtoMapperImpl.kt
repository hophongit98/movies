package com.example.egsassignment.data

import com.example.egsassignment.data.dto.moviedetail.*
import com.example.egsassignment.domain.model.moviedetail.*
import javax.inject.Inject

class MovieDetailDtoMapperImpl @Inject constructor() : MovieDetailDtoMapper {
    override fun toMovieDetail(movieDetailDTO: MovieDetailDTO): MovieDetail {
        return MovieDetail(
            adult = movieDetailDTO.adult,
            backdropPath = movieDetailDTO.backdropPath,
            belongsToCollection = movieDetailDTO.belongsToCollection?.toBelongsToCollection(),
            budget = movieDetailDTO.budget,
            genres = movieDetailDTO.genres.toGenre(),
            homepage = movieDetailDTO.homepage,
            id = movieDetailDTO.id,
            imdbId = movieDetailDTO.imdbId,
            originalLanguage = movieDetailDTO.originalLanguage,
            originalTitle = movieDetailDTO.originalTitle,
            overview = movieDetailDTO.overview,
            popularity = movieDetailDTO.popularity,
            posterPath = movieDetailDTO.posterPath,
            productionCompanies = movieDetailDTO.productionCompanies.toProductionCompany(),
            productionCountries = movieDetailDTO.productionCountries.toProductionCountry(),
            releaseDate = movieDetailDTO.releaseDate,
            revenue = movieDetailDTO.revenue,
            runtime = movieDetailDTO.runtime,
            spokenLanguages = movieDetailDTO.spokenLanguages.toSpokenLanguage(),
            status = movieDetailDTO.status,
            tagline = movieDetailDTO.tagline,
            title = movieDetailDTO.title,
            video = movieDetailDTO.video,
            voteCount = movieDetailDTO.voteCount,
            voteAverage = movieDetailDTO.voteAverage
        )
    }

    private fun BelongsToCollectionDTO.toBelongsToCollection(): BelongsToCollection {
        return BelongsToCollection(
            id = this.id,
            name = this.name,
            posterPath = this.posterPath,
            backdropPath = this.backdropPath
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