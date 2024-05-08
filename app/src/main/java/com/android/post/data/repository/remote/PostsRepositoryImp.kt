package com.android.post.data.repository.remote

import com.android.post.data.source.remote.ApiService
import com.android.post.domain.model.PostResponseWrapper

class PostsRepositoryImp(private val apiService: ApiService) : PostsRepository {

    override suspend fun getPosts(): PostResponseWrapper {
        return apiService.getPosts()
    }
}