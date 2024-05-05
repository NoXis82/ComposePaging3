package com.example.posts.repository

import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.data.safeApiCall
import com.example.domain.RepositoryError
import com.example.domain.RequestResult
import com.example.posts.mapper.mapToPostItem
import com.example.posts.model.PostItem
import com.example.posts.repository.remote.PostsRemoteApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postsRemote: PostsRemoteApi
) : PostRepository {
    override fun getAllPosts(): Flow<RequestResult<List<PostItem>, RepositoryError>> = flow {
        val result = safeApiCall { postsRemote.getAllPosts() }
        when (result) {
            is RequestResult.Success -> {
                emit(RequestResult.Success(result.data?.map { it.mapToPostItem() }))
            }

            is RequestResult.Error -> {
                emit(RequestResult.Error(result.error))
            }
        }
    }

}