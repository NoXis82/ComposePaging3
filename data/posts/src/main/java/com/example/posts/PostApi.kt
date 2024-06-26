package com.example.posts

import com.example.posts.model.PostDto
import retrofit2.Response
import retrofit2.http.GET

interface PostApi {

    @GET("posts")
    suspend fun getAllPosts(): Response<List<PostDto>>

}