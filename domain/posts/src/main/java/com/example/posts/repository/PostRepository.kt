package com.example.posts.repository

import com.example.domain.RepositoryError
import com.example.domain.RequestResult
import com.example.posts.model.PostItem
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getAllPosts(): Flow<RequestResult<List<PostItem>, RepositoryError>>

}