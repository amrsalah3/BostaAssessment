package com.bosta.assessment.amrsalah.domain

sealed interface Result<out R> {

    data class Success<out T>(val data: T) : Result<T>
    data class Failure(val exception: Exception? = null) : Result<Nothing>

    object Loading : Result<Nothing>
}
