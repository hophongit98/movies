package com.example.egsassignment.domain

/**
 * Created by Phillip Truong
 * date 01/03/2024.
 */
class ApiResult<T>(val data: T? = null, error: ErrorType? = null)

class ErrorType(
    val httpCode: Int,
    val errorMsg: String
)