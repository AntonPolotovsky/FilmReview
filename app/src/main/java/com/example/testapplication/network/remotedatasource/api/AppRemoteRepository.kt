package com.example.testapplication.network.remotedatasource.api

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.testapplication.network.remotedatasource.data.Result
import kotlinx.coroutines.flow.Flow


interface AppRemoteRepository {

    fun getMovies(): Flow<PagingData<Result>>

}