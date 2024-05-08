package com.android.post.data.source.remote

import com.android.post.domain.model.PostResponseWrapper
import retrofit2.http.GET

interface ApiService {

    @GET("/feed/@primoapp")
    suspend fun getPosts(): PostResponseWrapper
}