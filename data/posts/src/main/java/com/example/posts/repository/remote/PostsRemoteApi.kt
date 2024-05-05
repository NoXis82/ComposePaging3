package com.example.posts.repository.remote

import com.example.posts.model.PostDto
import retrofit2.Response

interface PostsRemoteApi {
    suspend fun getAllPosts(): Response<List<PostDto>>
}