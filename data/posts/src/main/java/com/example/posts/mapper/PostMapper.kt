package com.example.posts.mapper

import com.example.posts.model.PostDto
import com.example.posts.model.PostItem

fun PostDto.mapToPostItem(): PostItem {
    return PostItem(
        userId = this.userId,
        id = this.id,
        title = this.title,
        body = this.body
    )
}