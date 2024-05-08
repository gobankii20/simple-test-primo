package com.android.post.domain.usecase.local

import com.android.post.data.repository.local.PostsLocalRepository
import com.android.post.domain.usecase.base.UseCase

class DeletePostLocalUseCase constructor(
    private val postsLocalRepository: PostsLocalRepository
) : UseCase<Unit, Any>() {

    override suspend fun run(params: Any?) {
        return postsLocalRepository.deleteAll()
    }

}