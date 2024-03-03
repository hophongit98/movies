package com.example.egsassignment.domain.usecase

import com.example.egsassignment.domain.ApiResult
import com.example.egsassignment.domain.model.moviedetail.MovieDetail
import com.example.egsassignment.domain.repository.MovieRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

/**
 * Created by Phillip Truong
 * date 03/03/2024.
 */
class RetrieveMovieDetailUseCaseTest {

    @Mock
    lateinit var repository: MovieRepository

    lateinit var useCase: RetrieveMovieDetailUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = RetrieveMovieDetailUseCaseImpl(repository)
    }

    @Test
    fun givenReturnEmptyData_whenExecuted_thenReturnFail() = runBlocking {
        // given
        val id = 12345
        whenever(repository.retrieveMovieDetail(id)).thenReturn(flowOf(ApiResult()))

        // when
        val result = useCase.execute(RetrieveMovieDetailUseCase.Input(id))

        // then
        result.collect {
            assert(it is RetrieveMovieDetailUseCase.Result.Error)
        }
    }

    @Test
    fun givenReturnData_whenExecuted_thenReturnSuccess() = runBlocking {
        // given
        val id = 12345
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
        whenever(repository.retrieveMovieDetail(id)).thenReturn(flowOf(ApiResult(data = movieDetail)))

        // when
        val result = useCase.execute(RetrieveMovieDetailUseCase.Input(id))

        // then
        result.collect {
            assert(it is RetrieveMovieDetailUseCase.Result.Success)
        }
    }

}