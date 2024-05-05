package com.example.posts.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.posts.api.PostApi
import com.example.posts.api.PostPagingDataSource
import com.example.posts.model.PostDto
import com.example.posts.model.PostItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class PostsRemoteImpl @Inject constructor(
    private val postApi: PostApi
) : PostsRemoteApi {
    override suspend fun getAllPosts(): Response<List<PostDto>> =
        postApi.getAllPosts()

}