package com.example.testapplication.presentation.filmactivity.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.testapplication.network.localdatasource.MokData
import com.example.testapplication.network.remotedatasource.api.AppRemoteRepository
import com.example.testapplication.network.remotedatasource.data.Movies
import com.example.testapplication.network.remotedatasource.data.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import javax.inject.Inject

class FilmPageInteractorImpl @Inject constructor(
    private val mokData: MokData,
    private val appRemoteRepository: AppRemoteRepository
): FilmPageInteractor {
    override fun getFilmForDescription(): List<String?> {
        return mokData.getFilmDescription()
    }

    override fun getMovies() : Flow<PagingData<Result>> {
       return appRemoteRepository.getMovies()
    }

}
