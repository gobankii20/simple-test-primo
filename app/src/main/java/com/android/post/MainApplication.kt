package com.android.post

import android.app.Application
import androidx.multidex.MultiDex
import com.android.post.data.source.local.room.AppRoomDatabase
import com.android.post.di.module.AppModule
import com.android.post.di.module.NetworkModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module


class MainApplication : Application() {

    private val database by lazy { AppRoomDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            modules(listOf(AppModule, NetworkModule, appModule))
        }
    }

    private val appModule = module {
        single { database.postDao() }
    }

}