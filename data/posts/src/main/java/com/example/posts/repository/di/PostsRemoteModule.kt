package com.example.posts.repository.di

import com.example.posts.PostApi
import com.example.posts.repository.remote.PostsRemoteApi
import com.example.posts.repository.remote.PostsRemoteImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PostsRemoteModule {

    @Singleton
    @Provides
    fun providePostApi(retrofit: Retrofit): PostApi = retrofit.create(PostApi::class.java)

    @Singleton
    @Provides
    internal fun provideInitialPostsRemote(postApi: PostApi): PostsRemoteApi {
        return PostsRemoteImpl(postApi)
    }

}