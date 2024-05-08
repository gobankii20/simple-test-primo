package com.android.post.domain.usecase

import com.android.post.data.source.local.room.entity.PostEntity
import com.android.post.domain.model.PostResponseWrapper
import com.android.post.data.repository.local.PostsLocalRepository
import com.android.post.data.repository.remote.PostsRepository
import com.android.post.domain.usecase.base.UseCase

class GetPostsUseCase constructor(
    private val postsRepository: PostsRepository,
    private val postsLocalRepository: PostsLocalRepository
) : UseCase<PostResponseWrapper, Any?>() {

    override suspend fun run(params: Any?): PostResponseWrapper {
        mappingDataPost(postsRepository.getPosts())
        return postsRepository.getPosts()
    }

    private suspend fun mappingDataPost(postResponse: PostResponseWrapper) {
        val dataPostLocal = postsLocalRepository.getPosts()
        if (checkDataEmpty(dataPostLocal, postResponse)) {
            postResponse.channel?.itemList?.mapIndexed { index, postItem ->
                postsLocalRepository.insert(
                    postEntity = PostEntity(
                        id = index,
                        title = postItem.title,
                        body = postItem.content
                    )
                )
            }
        }
    }

    private fun checkDataEmpty(dataPostLocal: List<PostEntity>, postResponse: PostResponseWrapper): Boolean {
        return dataPostLocal.isEmpty() || dataPostLocal.size != postResponse.channel?.itemList?.size
    }

}