package com.example.egsassignment.domain.usecase

import com.example.egsassignment.domain.model.moviedetail.MovieDetail

/**
 * Created by Phillip Truong
 * date 28/02/2024.
 */
interface RetrieveMovieDetailUseCase :
    UseCase<RetrieveMovieDetailUseCase.Input, RetrieveMovieDetailUseCase.Result> {

    data class Input(val movieId: Int, val language: String = "en-US")

    sealed class Result {
        data class Success(val movieDetail: MovieDetail) : Result()
        data class Error(val errorCode: Int?) : Result()
    }
}