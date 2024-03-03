package com.example.egsassignment.domain.usecase

import com.example.egsassignment.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RetrieveMovieDetailUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : RetrieveMovieDetailUseCase {
    override fun execute(input: RetrieveMovieDetailUseCase.Input): Flow<RetrieveMovieDetailUseCase.Result> {
        return movieRepository.retrieveMovieDetail(
            movieId = input.movieId, language = input.language
        ).map {
            it.data?.let { data ->
                RetrieveMovieDetailUseCase.Result.Success(data)
            } ?: run {
                RetrieveMovieDetailUseCase.Result.Error(it.error?.httpCode)
            }
        }
    }
}