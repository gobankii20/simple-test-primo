package com.android.post.domain.usecase.local

import com.android.post.data.source.local.room.entity.PostEntity
import com.android.post.data.repository.local.PostsLocalRepository
import com.android.post.domain.usecase.base.UseCase

class GetPostsLocalUseCase constructor(
    private val postsLocalRepository: PostsLocalRepository
) : UseCase<List<PostEntity>, Any?>() {

    override suspend fun run(params: Any?): List<PostEntity> {
        return postsLocalRepository.getPosts()
    }

}