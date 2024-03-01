package com.example.egsassignment.domain.usecase

/**
 * Created by Phillip Truong
 * date 28/02/2024.
 */
interface UseCase<in Input, out Output> {
    suspend fun execute(input: Input): Output
}