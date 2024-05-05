package com.example.posts.api

import com.example.posts.model.PostDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {

    @GET("posts")
    suspend fun getAllPosts(): Response<List<PostDto>>

    @GET("posts")
    suspend fun getPagingAllPosts(
        @Query("_start") pager: Int,
        @Query("_limit") limit: Int
    ): Response<List<PostDto>>

}