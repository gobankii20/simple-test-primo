package com.android.post.data.repository.remote

import com.android.post.domain.model.PostResponseWrapper

interface PostsRepository {

    suspend fun getPosts(): PostResponseWrapper
}