package com.example.testapplication.ret

import com.example.testapplication.network.intersepter.MokResponseInterceptors
import com.example.testapplication.network.remotedatasource.api.AppApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class RetrofitFactory @Inject constructor(
) {

    private val client = OkHttpClient()
        .newBuilder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    fun getApiInterface() : AppApi {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppApi::class.java)
    }

}