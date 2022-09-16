package com.example.challengecode.di

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request().newBuilder()
//            .addHeader("apikey", "3a783b25c80e1c44875356dd363f272d")
//            .addHeader("ts", "1")
//            .build()
//        return chain.proceed(request)
//    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url: HttpUrl = request.url().newBuilder()
            .addQueryParameter("apikey", "3a783b25c80e1c44875356dd363f272d")
            .addQueryParameter("ts", "1")
            .build()
        request = request.newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }

}