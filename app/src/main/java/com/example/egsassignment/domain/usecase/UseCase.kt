package com.example.egsassignment.domain.usecase

import kotlinx.coroutines.flow.Flow

/**
 * Created by Phillip Truong
 * date 28/02/2024.
 */
interface UseCase<in Input, out Output> {
    fun execute(input: Input): Flow<Output>
}