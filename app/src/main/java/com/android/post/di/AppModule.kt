package com.android.post.di.module

import com.android.post.presentation.PostsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    viewModel { PostsViewModel(get(), get(), get()) }

    single { createGetPostsUseCase(get(), get()) }

    single { createGetPostsLocalUseCase(get()) }

    single { createPostsLocalUseCase(get()) }

    single { createPostRepository(get()) }

    single { createPostLocalRepository(get()) }

    single { createDeletePostsLocalUseCase(get()) }
}