package com.example.data

import com.example.domain.RepositoryError
import com.example.domain.RequestResult
import kotlinx.serialization.SerializationException
import retrofit2.Response
import java.nio.channels.UnresolvedAddressException
import kotlin.coroutines.cancellation.CancellationException


suspend inline fun <reified T> safeApiCall(api: () -> Response<T>): RequestResult<T, RepositoryError> {
    val response = try {
        api()
    } catch (e: UnresolvedAddressException) {
        e.printStackTrace()
        return RequestResult.Error(RepositoryError.Network.NO_INTERNET)
    } catch (e: SerializationException) {
        e.printStackTrace()
        return RequestResult.Error(RepositoryError.Network.SERIALIZATION)
    } catch (e: Exception) {
        if (e is CancellationException) throw e
        e.printStackTrace()
        return RequestResult.Error(RepositoryError.Network.UNKNOWN)
    }
    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(response: Response<T>): RequestResult<T, RepositoryError> {
    return if (response.isSuccessful) {
        RequestResult.Success(response.body())
    } else {
        when (response.code()) {
            401 -> RequestResult.Error(RepositoryError.Network.UNAUTHORIZED)
            408 -> RequestResult.Error(RepositoryError.Network.REQUEST_TIMEOUT)
            409 -> RequestResult.Error(RepositoryError.Network.CONFLICT)
            413 -> RequestResult.Error(RepositoryError.Network.PAYLOAD_TOO_LARGE)
            429 -> RequestResult.Error(RepositoryError.Network.TOO_MANY_REQUESTS)
            in 500..599 -> RequestResult.Error(RepositoryError.Network.SERVER_ERROR)
            else -> RequestResult.Error(RepositoryError.Network.UNKNOWN)
        }
    }
}