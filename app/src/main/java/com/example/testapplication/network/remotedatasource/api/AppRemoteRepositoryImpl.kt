package com.example.testapplication.network.remotedatasource.api

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.testapplication.app.NETWORK_PAGE_SIZE
import com.example.testapplication.network.remotedatasource.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AppRemoteRepositoryImpl @Inject constructor(
    private val appApi: AppApi
) : AppRemoteRepository {

     override fun getMovies(): Flow<PagingData<Result>> {

        return Pager(
                config = PagingConfig(
                    pageSize = NETWORK_PAGE_SIZE, // количестко отображаемых лементов на странице
        /*!!!!!*///            pageSize = 5,
                    enablePlaceholders = false,
     /*??????*/               initialLoadSize = 6,// первоначально апрашивает с сервера при первоначальной загрузке
                    prefetchDistance = 2,  // использует этот параметр, чтобы определить, когда надо подгружать следующую порцию данных. По умолчанию этот параметр равен pageSize. Можно задать свое значение.
          //      maxSize = 20

                ),
                pagingSourceFactory = {
                    MoviesSource(appApi = appApi)
                }
            ).flow
            .flowOn(Dispatchers.IO)

    }

}