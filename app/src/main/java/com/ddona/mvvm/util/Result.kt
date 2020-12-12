package com.ddona.mvvm.util

import java.lang.Exception

sealed class Result {
    data class Success(val data: Any) : Result()
    data class Error(val e: Exception) : Result()
    object Loading : Result()
}