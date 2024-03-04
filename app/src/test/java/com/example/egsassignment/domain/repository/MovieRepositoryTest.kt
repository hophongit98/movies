package com.example.egsassignment.domain.repository

import com.example.egsassignment.data.MovieDetailDtoMapper
import com.example.egsassignment.data.MovieListDtoMapper
import com.example.egsassignment.data.MovieRepositoryImpl
import com.example.egsassignment.data.dto.moviedetail.MovieDetailDTO
import com.example.egsassignment.data.remote.api.MovieApi
import com.example.egsassignment.domain.model.moviedetail.MovieDetail
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.*
import retrofit2.Response
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

/**
 * Created by Phillip Truong
 * date 03/03/2024.
 */
class MovieRepositoryTest {

    @Mock
    lateinit var movieApi: MovieApi

    @Mock
    lateinit var movieDetailMapper: MovieDetailDtoMapper

    @Mock
    lateinit var movieListMapper: MovieListDtoMapper

    lateinit var repository: MovieRepository

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

    val movieDetail = MovieDetail(
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

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = MovieRepositoryImpl(movieApi, movieListMapper, movieDetailMapper)
    }

    @Test
    fun givenRetrieveMovieDetailSuccessfully_whenRetrieveMovieDetail_thenReturnDataCorrectly() = runBlocking {
        // given
        val response = Response.success(dto)
        whenever(movieApi.retrieveMovieDetail(123)).thenReturn(response)
        whenever(movieDetailMapper.toMovieDetail(dto)).thenReturn(movieDetail)

        // when
        val result = repository.retrieveMovieDetail(123)

        // then
        result.collect {
            assertNotNull(it.data)
            assertNull(it.error)
            assertEquals("title", it.data!!.originalTitle)
        }
    }

    @Test
    fun givenRetrieveMovieDetailFailed_whenRetrieveMovieDetail_thenReturnErrorType() = runBlocking {
        // given
        val response = Response.error<MovieDetailDTO>(400, mock())
        whenever(movieApi.retrieveMovieDetail(123)).thenReturn(response)

        // when
        val result = repository.retrieveMovieDetail(123)

        // then
        result.collect {
            assertNull(it.data)
            assertNotNull(it.error)
            assertEquals(400, it.error!!.httpCode)
        }
    }
}