package com.mohamedabdelaziz.core.network.common

import retrofit2.Response

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful && response.body() != null) {
            NetworkResult.Success(response.body()!!)
        } else {
            NetworkResult.Error(response.message(), response.code())
        }
    } catch (e: Exception) {
        NetworkResult.Error(e.localizedMessage ?: "Unknown error")
    }
}
