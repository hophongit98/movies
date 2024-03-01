package com.example.egsassignment.domain.usecase

import com.example.egsassignment.domain.model.moviedetail.MovieDetails
import kotlinx.coroutines.flow.Flow

/**
 * Created by Phillip Truong
 * date 28/02/2024.
 */
interface RetrieveMovieDetailUseCase : UseCase<RetrieveMovieDetailUseCase.Input, Flow<RetrieveMovieDetailUseCase.Result>> {

    data class Input(val movieId: Int, val language: String)

    sealed class Result {
        data class Success(val movieDetails: MovieDetails): Result()
        object Error: Result()
    }
}