package com.example.posts.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.posts.api.PostApi
import com.example.posts.api.PostPagingDataSource
import com.example.posts.model.PostItem
import com.example.posts.repository.PostRepository
import com.example.posts.repository.PostRepositoryImpl
import com.example.posts.repository.remote.PostsRemoteApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PostModule {

    @Provides
    @Singleton
    fun providePostRepository(postsRemote: PostsRemoteApi): PostRepository {
        return PostRepositoryImpl(postsRemote)
    }

    @Provides
    @Singleton
    fun providePostPager(postApi: PostApi): Pager<Int, PostItem> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { PostPagingDataSource(postApi) }
        )
    }

}