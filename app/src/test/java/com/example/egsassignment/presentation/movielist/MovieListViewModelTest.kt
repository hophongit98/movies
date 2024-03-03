package com.example.egsassignment.presentation.movielist

import com.example.egsassignment.base.ViewModelBaseTest
import com.example.egsassignment.domain.model.movielist.MovieList
import com.example.egsassignment.domain.usecase.RetrieveMovieListUseCase
import com.example.egsassignment.presentation.features.movielist.MovieListViewHolder
import com.example.egsassignment.presentation.features.movielist.MovieListViewModel
import com.example.egsassignment.util.LiveDataTestObserver
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.math.absoluteValue
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * Created by Phillip Truong
 * date 03/03/2024.
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieListViewModelTest : ViewModelBaseTest() {

    lateinit var viewModel: MovieListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = MovieListViewModel()
    }

    @Test
    fun verifyOnServiceBound() {
        val observer = LiveDataTestObserver(viewModel.loadPage)

        // when
        viewModel.onServiceBound()

        // then
        assertEquals(1, observer.observedValues.size)
        assertEquals(1, observer.observedValues[0]!!.absoluteValue)
    }

    @Test
    fun verifyOnMovieItemSelected() {
        val observer = LiveDataTestObserver(viewModel.navigateToMovieDetail)

        // when
        viewModel.onMovieItemSelected(123)

        // then
        assertEquals(1, observer.observedValues.size)
        assertEquals(123, observer.observedValues[0]!!.absoluteValue)
    }

    @Test
    fun verifyLoadNextPage() {
        val observer = LiveDataTestObserver(viewModel.loadPage)

        // when
        viewModel.loadNextPage()

        // then
        assertEquals(1, observer.observedValues.size)
        assertEquals(2, observer.observedValues[0]!!.absoluteValue)
    }

    @Test
    fun verifyOnMoviesRetrieved() {
        val observer = LiveDataTestObserver(viewModel.movies)

        // when
        viewModel.onMoviesRetrieved(RetrieveMovieListUseCase.Result.Success(movieList = MovieList(
            page = 1,
            results = emptyList(),
            totalPages = 10,
            totalResults = 100)))

        // then
        assertEquals(1, observer.observedValues.size)
        val displayItems = observer.observedValues[0]
        assertNotNull(displayItems)
        assertEquals(1, displayItems.size) // header
        assert(displayItems[0] is MovieListViewHolder.DisplayItem.Header)
    }
}