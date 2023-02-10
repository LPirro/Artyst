package com.lpirro.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header("Accept", "application/json")
            .header("User-agent", "Artyst")
            .build()
        return chain.proceed(request)
    }
}
