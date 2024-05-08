package com.android.post.data.repository.local

import com.android.post.data.source.local.room.dao.PostDao
import com.android.post.data.source.local.room.entity.PostEntity

class PostsLocalRepositoryImp(private val postDao: PostDao) : PostsLocalRepository {

    override suspend fun getPosts(): List<PostEntity> {
        return postDao.getPosts()
    }

    override suspend fun insert(postEntity: PostEntity) {
        return postDao.insert(postEntity = postEntity)
    }

    override suspend fun deleteAll() {
        return postDao.deleteAll()
    }
}