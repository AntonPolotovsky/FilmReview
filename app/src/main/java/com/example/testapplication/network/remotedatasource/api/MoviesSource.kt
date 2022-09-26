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
) : PagingSource<Int, Result>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val pageIndex = params.key ?: TMBD_STARTING_PAGE_INDEX     //  start from  1

        try {

            val response = appApi.getAllMovies(apiKey = API_KEY, page = pageIndex)


            val movies = response.results


            val nextKey = if (response.has_more == true) {
                null
            } else {
                pageIndex + 1

            }




            return LoadResult.Page(
                data = movies,

                prevKey = if (pageIndex == TMBD_STARTING_PAGE_INDEX) null else pageIndex,

                nextKey = nextKey


            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    }
}