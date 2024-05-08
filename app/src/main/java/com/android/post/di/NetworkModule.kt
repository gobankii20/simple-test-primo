package com.android.post.di.module

import com.android.post.BuildConfig
import com.android.post.data.repository.remote.PostsRepositoryImp
import com.android.post.data.repository.local.PostsLocalRepositoryImp
import com.android.post.data.source.local.room.dao.PostDao
import com.android.post.data.source.remote.ApiService
import com.android.post.data.repository.local.PostsLocalRepository
import com.android.post.data.repository.remote.PostsRepository
import com.android.post.domain.usecase.GetPostsUseCase
import com.android.post.domain.usecase.local.DeletePostLocalUseCase
import com.android.post.domain.usecase.local.GetPostsLocalUseCase
import com.android.post.domain.usecase.local.PostLocalUseCase
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L

val NetworkModule = module {

    single { createService(get()) }

    single { createRetrofit(get(), BuildConfig.BASE_URL) }

    single { createOkHttpClient() }

    single { MoshiConverterFactory.create() }

    single { Moshi.Builder().build() }

}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create()).build()
}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun createPostRepository(apiService: ApiService): PostsRepository {
    return PostsRepositoryImp(apiService)
}

fun createPostLocalRepository(postDao: PostDao): PostsLocalRepository {
    return PostsLocalRepositoryImp(postDao)
}

fun createGetPostsUseCase(postsRepository: PostsRepository, postsLocalRepository: PostsLocalRepository): GetPostsUseCase {
    return GetPostsUseCase(postsRepository,postsLocalRepository)
}

fun createGetPostsLocalUseCase(postsLocalRepository: PostsLocalRepository): GetPostsLocalUseCase {
    return GetPostsLocalUseCase(postsLocalRepository)
}

fun createPostsLocalUseCase(postsLocalRepository: PostsLocalRepository): PostLocalUseCase {
    return PostLocalUseCase(postsLocalRepository)
}


fun createDeletePostsLocalUseCase(postsLocalRepository: PostsLocalRepository): DeletePostLocalUseCase {
    return DeletePostLocalUseCase(postsLocalRepository)
}
