package com.example.egsassignment.domain.usecase

import android.util.Log
import com.example.egsassignment.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RetrieveMovieListUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : RetrieveMovieListUseCase {
    override fun execute(input: RetrieveMovieListUseCase.Input): Flow<RetrieveMovieListUseCase.Result> {
        Log.d("Phillip", "RetrieveMovieListUseCase - execute")
        return movieRepository.retrieveMovieList().flowOn(Dispatchers.IO).map {
            Log.d("Phillip", "RetrieveMovieListUseCase - return data")
            it.data?.let { data ->
                RetrieveMovieListUseCase.Result.Success(data)
            } ?: run {
                RetrieveMovieListUseCase.Result.Error(it.error?.httpCode)
            }
        }
    }
}