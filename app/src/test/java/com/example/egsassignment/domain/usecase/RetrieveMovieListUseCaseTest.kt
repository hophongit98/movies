package com.example.egsassignment.domain.usecase

import com.example.egsassignment.domain.ApiResult
import com.example.egsassignment.domain.model.movielist.MovieList
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
class RetrieveMovieListUseCaseTest {

    @Mock
    lateinit var repository: MovieRepository

    lateinit var useCase: RetrieveMovieListUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = RetrieveMovieListUseCaseImpl(repository)
    }

    @Test
    fun givenReturnEmptyData_whenExecuted_thenReturnFail() = runBlocking {
        // given
        val page = 1
        whenever(repository.retrieveMovieList(page)).thenReturn(flowOf(ApiResult()))

        // when
        val result = useCase.execute(RetrieveMovieListUseCase.Input(page = page))

        // then
        result.collect {
            assert(it is RetrieveMovieListUseCase.Result.Error)
        }
    }

    @Test
    fun givenReturnData_whenExecuted_thenReturnSuccess() = runBlocking {
        // given
        val page = 1
        val movieList = MovieList(
            page = 1,
            results = listOf(),
            totalResults = 100,
            totalPages = 10
        )
        whenever(repository.retrieveMovieList(page)).thenReturn(flowOf(ApiResult(data = movieList)))

        // when
        val result = useCase.execute(RetrieveMovieListUseCase.Input(page = page))

        // then
        result.collect {
            assert(it is RetrieveMovieListUseCase.Result.Success)
        }
    }

}