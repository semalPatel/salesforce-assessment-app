package com.salesforce.mivi.network

import com.salesforce.mivi.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import kotlin.jvm.Throws

/**
 * Intercepts and adds apiKey as query param to all outgoing requests
 */
class AuthInterceptor : Interceptor {

    @Throws(IllegalStateException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url().newBuilder().addQueryParameter(API_KEY, BuildConfig.API_KEY_VALUE).build()
        request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    companion object {
        private const val API_KEY = "apikey"
    }

}