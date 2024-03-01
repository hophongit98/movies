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
    override suspend fun retrieveMovieList(): Flow<MovieList> {
        return movieRemoteDataSource.retrieveMovieList().map {
            movieListDtoMapper.toMovieList(it)
        }
    }

    override suspend fun retrieveMovieDetail(movieId: Int): Flow<MovieDetails> {
        return movieRemoteDataSource.retrieveMovieDetail(movieId).map {
            movieDetailDtoMapper.toMovieDetail(it)
        }
    }
}