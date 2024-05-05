package com.example.posts.repository.remote

import com.example.posts.PostApi
import com.example.posts.model.PostDto
import retrofit2.Response
import javax.inject.Inject

class PostsRemoteImpl @Inject constructor(
    private val postApi: PostApi
) : PostsRemoteApi {
    override suspend fun getAllPosts(): Response<List<PostDto>> =
        postApi.getAllPosts()

}