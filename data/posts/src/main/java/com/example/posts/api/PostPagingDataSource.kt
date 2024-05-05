package com.example.posts.api

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.safeApiCall
import com.example.domain.RequestResult
import com.example.posts.mapper.mapToPostItem
import com.example.posts.model.PostItem

class PostPagingDataSource(
    private val postApi: PostApi
) : PagingSource<Int, PostItem>() {
    override fun getRefreshKey(state: PagingState<Int, PostItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostItem> {
        return try {
            val page = params.key ?: 1
            val limit = 20
            val response = safeApiCall { postApi.getPagingAllPosts(page, limit) }
            when (response) {
                is RequestResult.Success -> {
                    val nextKey = if (response.data.isNullOrEmpty()) null else checkNotNull(response.data).size.plus(page).plus(1)
                    val prevKey = if (page == 1) null else checkNotNull(response.data).size.minus(limit)
                    Log.d(
                        TAG,
                        "page: $page, response.size: ${response.data?.size}, nextKey: $nextKey, prevKey: $prevKey"
                    )
                    LoadResult.Page(
                        data = response.data!!.map { it.mapToPostItem() },
                        nextKey = nextKey,
                        prevKey = prevKey
                    )
                }

                is RequestResult.Error -> {
                    Log.e(TAG, response.error.toString())
                    LoadResult.Error(Exception(response.error.toString()))
                }
            }

        } catch (e: Exception) {
             LoadResult.Error(e)
        }
    }

    companion object {
        private const val TAG = "PostPagingDataSource"
    }

}