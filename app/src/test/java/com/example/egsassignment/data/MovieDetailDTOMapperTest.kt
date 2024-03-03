package com.example.egsassignment.data

import com.example.egsassignment.data.dto.moviedetail.MovieDetailDTO
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals
import kotlin.test.assertFalse

/**
 * Created by Phillip Truong
 * date 03/03/2024.
 */
class MovieDetailDTOMapperTest {

    lateinit var mapper: MovieDetailDtoMapper

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mapper = MovieDetailDtoMapperImpl()
    }

    @Test
    fun givenDto_whenMapping_thenReturnCorrectly() {
        // given
        val dto = MovieDetailDTO(
            adult = false,
            backdropPath = "backdropPath",
            belongsToCollection = null,
            budget = 134L,
            genres = listOf(),
            homepage = "homepage",
            id = 12345,
            imdbId = "312",
            originalTitle = "title",
            originalLanguage = "en",
            overview = "overview",
            posterPath = "posterPath",
            popularity = 5.6,
            productionCountries = listOf(),
            productionCompanies = listOf(),
            releaseDate = "releaseDate",
            revenue = 123,
            runtime = 90,
            spokenLanguages = listOf(),
            status = "status",
            tagline = "tagline",
            title = "Title",
            video = false,
            voteAverage = 5.6,
            voteCount = 123,
        )

        // when
        val result = mapper.toMovieDetail(dto)

        // then
        assertEquals("title", result.originalTitle)
        assertEquals("Title", result.title)
        assertEquals("status", result.status)
        assertEquals("tagline", result.tagline)
        assertEquals("releaseDate", result.releaseDate)
        assertEquals("posterPath", result.posterPath)
        assertEquals("overview", result.overview)
        assertEquals("homepage", result.homepage)
        assertEquals(90, result.runtime)
        assertEquals(123, result.voteCount)
        assertEquals(5.6, result.voteAverage)
        assertFalse(result.adult)
        assertFalse(result.video)
    }
}