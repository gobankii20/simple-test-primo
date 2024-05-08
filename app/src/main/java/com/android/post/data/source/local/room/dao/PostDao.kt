package com.android.post.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.post.data.source.local.room.TB_POST_NAME
import com.android.post.data.source.local.room.entity.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM $TB_POST_NAME")
    suspend fun getPosts(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(postEntity: PostEntity)

    @Query("DELETE FROM $TB_POST_NAME")
    suspend fun deleteAll()
}