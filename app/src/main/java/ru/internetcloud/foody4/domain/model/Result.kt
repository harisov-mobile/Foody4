package ru.internetcloud.foody4.domain.model

sealed class Result<out T> {

    data class Success<T>(var data: T) : Result<T>()

    data class Error(val exception: Exception) : Result<Nothing>()

    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Loading -> "Loading"
        }
    }
}
