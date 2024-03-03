package com.example.egsassignment.data

import com.example.egsassignment.data.dto.movielist.MovieItemDTO
import com.example.egsassignment.data.dto.movielist.MovieListResponse
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

/**
 * Created by Phillip Truong
 * date 03/03/2024.
 */
class MovieListDTOMapperTest {

    lateinit var mapper: MovieListDtoMapper

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mapper = MovieListDtoMapperImpl()
    }

    @Test
    fun givenMovieListResponse_whenMapping_thenReturnCorrectly() {
        // given
        val response = MovieListResponse<List<MovieItemDTO>>(
            page = 1,
            totalPages = 10,
            totalResults = 100,
            results = listOf<MovieItemDTO>()
        )

        // when
        val result = mapper.toMovieList(response)

        // then
        assertEquals(1, response.page)
        assertEquals(10, response.totalPages)
        assertEquals(100, response.totalResults)
        assertEquals(0, response.results.size)
    }

}