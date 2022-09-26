package com.example.testapplication.network.intersepter

import com.example.testapplication.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor

object InterceptorFactory {
    fun createHttpLoggingInterceptor():HttpLoggingInterceptor{
        return HttpLoggingInterceptor()
            .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
    }

}