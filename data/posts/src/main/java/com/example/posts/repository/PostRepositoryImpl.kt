package com.example.posts.repository

import com.example.data.safeApiCall
import com.example.domain.RepositoryError
import com.example.domain.RequestResult
import com.example.posts.PostApi
import com.example.posts.mapper.mapToPostItem
import com.example.posts.model.PostItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostRepositoryImpl(
    private val postApi: PostApi
) : PostRepository {
    override fun getAllPosts(): Flow<RequestResult<List<PostItem>, RepositoryError>> = flow {
        val result = safeApiCall { postApi.getAllPosts() }
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