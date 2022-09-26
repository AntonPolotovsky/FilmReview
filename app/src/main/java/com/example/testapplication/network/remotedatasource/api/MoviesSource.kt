package com.example.testapplication.network.remotedatasource.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testapplication.app.API_KEY
import com.example.testapplication.app.NETWORK_PAGE_SIZE
import com.example.testapplication.app.TMBD_STARTING_PAGE_INDEX
import com.example.testapplication.network.remotedatasource.data.Result
import retrofit2.HttpException
import java.io.IOException


class MoviesSource(
    private val appApi: AppApi
): PagingSource<Int, Result>() {



    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val pageIndex = params.key?: TMBD_STARTING_PAGE_INDEX     //  start from  1
    //    val offset = if (params.key != null) ((pageIndex-1)* NETWORK_PAGE_SIZE) + 1 else TMBD_STARTING_PAGE_INDEX

         try {

          //  val response = appApi.getAllMovies(API_KEY)
            val response = appApi.getAllMovies(apiKey = API_KEY, page = pageIndex)
            // val response = appApi.getAllMovies(pageIndex)

            val movies = response.results



           //  val nextKey = if (response.isNotEmpty())
             val nextKey = if (response.has_more == true)
             {
                 null
             }else {
                 pageIndex + 1 // (params.loadSize / NETWORK_PAGE_SIZE)

             }

//           val nextKey = if (movies.isEmpty()) {
//                null
//            } else {
//    /*11111*/  //          pageIndex + (NETWORK_PAGE_SIZE)
//                pageIndex +  (params.loadSize / NETWORK_PAGE_SIZE)
//            }


             return  LoadResult.Page(
                data = movies,
        //        prevKey = if (pageIndex == 1) null else pageIndex - 1,
                 prevKey = if (pageIndex == TMBD_STARTING_PAGE_INDEX) null else pageIndex,

                 nextKey = nextKey

        //     nextKey = null

             )
        }
        catch (e:IOException) {
            return LoadResult.Error(e)
        }
        catch (e:HttpException) {
            return LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.let { anchorPage ->
//                val pageIndex = Result.indexOf(anchorPage)
//                if (pageIndex == 0) {
//                    null
//                } else {
//                    Result[pageIndex - 1].nextKey
//                }
//            }
    }
}