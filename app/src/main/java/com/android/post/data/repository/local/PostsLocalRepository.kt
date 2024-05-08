package com.android.post.data.repository.local

import com.android.post.data.source.local.room.entity.PostEntity

interface PostsLocalRepository {

    suspend fun getPosts(): List<PostEntity>

    suspend fun insert(postEntity: PostEntity)

    suspend fun deleteAll()
}