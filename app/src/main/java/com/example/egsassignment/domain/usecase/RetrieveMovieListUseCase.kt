package com.example.egsassignment.domain.usecase

import com.example.egsassignment.domain.model.movielist.MovieList
import kotlinx.coroutines.flow.Flow

/**
 * Created by Phillip Truong
 * date 28/02/2024.
 */
interface RetrieveMovieListUseCase :
    UseCase<RetrieveMovieListUseCase.Input, Flow<RetrieveMovieListUseCase.Result>> {
    data class Input(val page: Int, val language: String)

    sealed class Result {
        data class Success(val movieList: MovieList) : Result()
        data class Error(val errorCode: Int?) : Result()
    }
}