package com.example.egsassignment.presentation.moviedetail

import com.example.egsassignment.base.ViewModelBaseTest
import com.example.egsassignment.domain.model.moviedetail.MovieDetail
import com.example.egsassignment.domain.usecase.RetrieveMovieDetailUseCase
import com.example.egsassignment.presentation.features.moviedetail.MovieDetailContract
import com.example.egsassignment.presentation.features.moviedetail.MovieDetailViewModel
import com.example.egsassignment.util.LiveDataTestObserver
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.math.absoluteValue
import kotlin.test.assertEquals

/**
 * Created by Phillip Truong
 * date 03/03/2024.
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModel : ViewModelBaseTest() {

    lateinit var viewModel: MovieDetailViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = MovieDetailViewModel()
    }

    @Test
    fun verifyInitialise() {
        val observer = LiveDataTestObserver(viewModel.loadMovieDetail)

        // when
        viewModel.initialise(123)

        // then
        assertEquals(1, observer.observedValues.size)
        assertEquals(123, observer.observedValues[0]!!.absoluteValue)
    }

    @Test
    fun verifyOnServiceBound() {
        val observer = LiveDataTestObserver(viewModel.loadMovieDetail)

        // when
        viewModel.onServiceBound()

        // then
        assertEquals(1, observer.observedValues.size)
    }

    @Test
    fun verifyOnMovieDetailRetrieved() = runBlocking {
//        val observer = LiveDataTestObserver(viewModel.movie)
//        val movieDetail = MovieDetail(
//            adult = false,
//            backdropPath = "backdropPath",
//            belongsToCollection = null,
//            budget = 134L,
//            genres = listOf(),
//            homepage = "homepage",
//            id = 12345,
//            imdbId = "312",
//            originalTitle = "title",
//            originalLanguage = "en",
//            overview = "overview",
//            posterPath = "posterPath",
//            popularity = 5.6,
//            productionCountries = listOf(),
//            productionCompanies = listOf(),
//            releaseDate = "2024-03-03",
//            revenue = 123,
//            runtime = 90,
//            spokenLanguages = listOf(),
//            status = "status",
//            tagline = "tagline",
//            title = "Title",
//            video = false,
//            voteAverage = 5.6,
//            voteCount = 123,
//        )
//
//        // when
//        viewModel.onMovieDetailRetrieved(RetrieveMovieDetailUseCase.Result.Success(movieDetail = movieDetail))
//
//        // then
//        assertEquals(1, observer.observedValues.size)
//        assert(observer.observedValues[0] is MovieDetailContract.MovieDetailDisplayable)
    }
}