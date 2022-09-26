package com.example.testapplication.presentation.filmactivity.domain

import androidx.paging.PagingData
import com.example.testapplication.network.remotedatasource.data.Movies
import com.example.testapplication.network.remotedatasource.data.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

interface FilmPageInteractor {
    fun getFilmForDescription():List<String?>

    fun getMovies(): Flow<PagingData<Result>>
}