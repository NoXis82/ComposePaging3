package com.example.posts.di

import com.example.posts.PostApi
import com.example.posts.repository.PostRepository
import com.example.posts.repository.PostRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PostModule {

    @Singleton
    @Provides
    fun providePostApi(retrofit: Retrofit): PostApi = retrofit.create(PostApi::class.java)

    @Provides
    @Singleton
    fun providePostRepository(postApi: PostApi): PostRepository {
        return PostRepositoryImpl(postApi)
    }
}