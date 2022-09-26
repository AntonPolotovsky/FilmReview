package com.example.testapplication.network.remotedatasource.api

import com.example.testapplication.network.remotedatasource.data.Movies
import retrofit2.http.GET
import retrofit2.http.Query


interface AppApi {

    @GET("svc/movies/v2/reviews/all.json")
    suspend fun getAllMovies(
        @Query ("api-key", encoded = true) apiKey: String,
        @Query ("page") page: Int
    ): Movies

}