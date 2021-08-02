package com.salesforce.mivi

import android.util.Log
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun <T: Any> apiCall(call: suspend ()->Response<T>): Result<T> {
    return try {
        val response = call()
        if (response.isSuccessful) {
            Result.Success(response.body()!!)
        } else {
            Result.Failure(Throwable("Error in getting the response"))
        }
    } catch (e: Throwable) {
        Result.Failure(e)
    }
}
