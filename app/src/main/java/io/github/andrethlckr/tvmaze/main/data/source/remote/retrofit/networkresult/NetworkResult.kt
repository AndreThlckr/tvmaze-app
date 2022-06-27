package io.github.andrethlckr.tvmaze.main.data.source.remote.retrofit.networkresult

/**
 * A simple class for distinguishing successful network calls from Not Found errors and other
 * types of failure.
 */
sealed class NetworkResult<out T> {

    data class Success<out T>(val data: T): NetworkResult<T>()

    object NotFoundError: NetworkResult<Nothing>()

    object Failure: NetworkResult<Nothing>()
}

/**
 * Utility method for transforming the data inside a NetworkResult.
 *
 * If the result is Success, the transformation is performed.
 *
 * If the result is a Failure, it is simply forwarded.
 */
inline fun <T, R> NetworkResult<T>.map(transform: (T) -> R): NetworkResult<R> {
    return when(this) {
        is NetworkResult.Success -> NetworkResult.Success(transform(data))
        is NetworkResult.NotFoundError -> this
        is NetworkResult.Failure -> this
    }
}

/**
 * Utility method for transforming the data inside a list inside a NetworkResult.
 *
 * If the result is Success, the transformation is performed on each item of the list.
 *
 * If the result is a Failure, it is simply forwarded.
 */
inline fun <T, R> NetworkResult<List<T>>.mapList(
    transform: (T) -> R
): NetworkResult<List<R>> = map { it.map(transform) }

/**
 * Return the data if the result is Success or null if it is a Failure.
 */
fun <T> NetworkResult<T>.dataOrNull(): T? {
    return when(this) {
        is NetworkResult.Success -> this.data
        is NetworkResult.NotFoundError -> null
        is NetworkResult.Failure -> null
    }
}
