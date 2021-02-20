package com.geermank.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

abstract class BaseApi {

    protected inline fun <reified T> buildService(): T {
        return Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .client(getClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(T::class.java)
    }

    protected open fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

    abstract fun getBaseUrl(): String
}
