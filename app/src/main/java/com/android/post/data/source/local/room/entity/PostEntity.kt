package com.android.post.data.source.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.post.data.source.local.room.TB_POST_NAME

@Entity(tableName = TB_POST_NAME)
data class PostEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "body") val body: String
)
