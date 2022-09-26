package com.example.testapplication.presentation.filmactivity.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.testapplication.network.remotedatasource.data.Movies
import com.example.testapplication.network.remotedatasource.data.Result
import com.example.testapplication.presentation.filmactivity.domain.FilmPageInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Call
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FilmActivityViewModel @Inject constructor(
    private val interactor: FilmPageInteractor

): ViewModel() {


    private val _moviesLiveData = MutableLiveData<Result>()
    //val allMoviesLiveData: LiveData<Result> get() = _moviesLiveData



   // @SuppressLint("CheckResult")
   fun getMovies(): Flow<PagingData<Result>> {

//        val response = interactor.getMovies().cachedIn(viewModelScope)
//        _moviesLiveData.value = response
//        return response
        return interactor.getMovies()
//            .map {pagingData ->
//                pagingData.map {
//                    _moviesLiveData.value = it
//                }
//
//            }
            .onEach { it ->
                it.map {
                    _moviesLiveData.value = it
                }
            }
            .cachedIn(viewModelScope)
    }
}
