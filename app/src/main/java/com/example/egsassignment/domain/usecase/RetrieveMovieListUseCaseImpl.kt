package com.example.egsassignment.domain.usecase

import com.example.egsassignment.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RetrieveMovieListUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : RetrieveMovieListUseCase {
    override suspend fun execute(input: RetrieveMovieListUseCase.Input): Flow<RetrieveMovieListUseCase.Result> {
        return movieRepository.retrieveMovieList().map {
            it.data?.let { data ->
                RetrieveMovieListUseCase.Result.Success(data)
            } ?: run {
                RetrieveMovieListUseCase.Result.Error(it.error?.httpCode)
            }
        }
    }
}