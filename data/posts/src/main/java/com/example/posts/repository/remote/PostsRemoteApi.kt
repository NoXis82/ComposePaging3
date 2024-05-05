package com.example.posts.repository.remote

import androidx.paging.PagingData
import com.example.posts.model.PostDto
import com.example.posts.model.PostItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface PostsRemoteApi {
    suspend fun getAllPosts(): Response<List<PostDto>>
}