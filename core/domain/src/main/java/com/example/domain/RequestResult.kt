package com.example.domain

typealias RootError = Error

sealed interface RequestResult<out D, out E : RootError> {
    data class Success<out D>(val data: D?) : RequestResult<D, Nothing>
    data class Error<out E : RootError>(val error: E) : RequestResult<Nothing, E>

}