package com.example.egsassignment.data

import com.example.egsassignment.data.remote.MovieRemoteDataSource
import com.example.egsassignment.domain.model.moviedetail.MovieDetails
import com.example.egsassignment.domain.model.movielist.MovieList
import com.example.egsassignment.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieListDtoMapper: MovieListDtoMapper,
    private val movieDetailDtoMapper: MovieDetailDtoMapper

) : MovieRepository {
    override suspend fun retrieveMovieList(page: Int, language: String): Flow<MovieList> {
        return movieRemoteDataSource.retrieveMovieList(page, language).map {
            movieListDtoMapper.toMovieList(it)
        }
    }

    override suspend fun retrieveMovieDetail(movieId: Int, language: String): Flow<MovieDetails> {
        return movieRemoteDataSource.retrieveMovieDetail(movieId, language).map {
            movieDetailDtoMapper.toMovieDetail(it)
        }
    }
}