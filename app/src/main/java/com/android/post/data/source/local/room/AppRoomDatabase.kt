package com.android.post.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.post.data.source.local.room.dao.PostDao
import com.android.post.data.source.local.room.entity.PostEntity

const val TB_POST_NAME = "tb_post"
const val DB_NAME = "post_database"

@Database(entities = arrayOf(PostEntity::class), version = 2, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context): AppRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}