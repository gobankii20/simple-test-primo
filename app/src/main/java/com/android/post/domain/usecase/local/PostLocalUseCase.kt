package com.android.post.domain.usecase.local

import com.android.post.data.source.local.room.entity.PostEntity
import com.android.post.data.repository.local.PostsLocalRepository
import com.android.post.domain.usecase.base.UseCase

class PostLocalUseCase constructor(
    private val postsLocalRepository: PostsLocalRepository
) : UseCase<Unit, PostEntity>() {

    override suspend fun run(params: PostEntity?) {
        return postsLocalRepository.insert(postEntity = params!!)
    }

}