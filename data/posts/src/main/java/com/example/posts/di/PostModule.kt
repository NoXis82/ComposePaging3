package com.example.posts.di

import com.example.posts.PostApi
import com.example.posts.repository.PostRepository
import com.example.posts.repository.PostRepositoryImpl
import com.example.posts.repository.remote.PostsRemoteApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PostModule {

    @Provides
    @Singleton
    fun providePostRepository(postsRemote: PostsRemoteApi): PostRepository {
        return PostRepositoryImpl(postsRemote)
    }
}