package com.android.post.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.post.data.source.local.room.entity.PostEntity
import com.android.post.domain.model.ApiError
import com.android.post.domain.model.Post
import com.android.post.domain.model.PostResponseWrapper
import com.android.post.domain.usecase.GetPostsUseCase
import com.android.post.domain.usecase.base.UseCaseResponse
import com.android.post.domain.usecase.local.DeletePostLocalUseCase
import com.android.post.domain.usecase.local.GetPostsLocalUseCase
import kotlinx.coroutines.cancel


class PostsViewModel constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val getPostLocalUseCase: GetPostsLocalUseCase,
    private val deletePostLocalUseCase: DeletePostLocalUseCase
) : ViewModel() {

    val postsData = MutableLiveData<List<Post>>()
    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun getPosts() {
        showProgressbar.value = true
        getPostsUseCase.invoke(
            viewModelScope, null,
            object : UseCaseResponse<PostResponseWrapper> {
                override fun onSuccess(result: PostResponseWrapper) {
                    Log.i(TAG, "result: $result")
                    showProgressbar.value = false
                    getDataPostsLocal()
                }

                override fun onError(apiError: ApiError?) {
                    messageData.value = apiError?.getErrorMessage()
                    Log.i(TAG, "error : ${apiError?.getErrorMessage()}")
                    showProgressbar.value = false
                }
            },
        )
    }

    private fun getDataPostsLocal() {
        showProgressbar.value = true
        getPostLocalUseCase.invoke(
            viewModelScope, null,
            object : UseCaseResponse<List<PostEntity>> {
                override fun onSuccess(result: List<PostEntity>) {
                    Log.i(TAG, "result local : ${result.size}")
                    val listPost = ArrayList<Post>()
                    result.forEachIndexed { index, postEntity ->
                        listPost.add(
                            Post(
                                id = postEntity.id,
                                title = postEntity.title,
                                body = postEntity.body
                            )
                        )
                    }
                    postsData.postValue(listPost)
                    showProgressbar.value = false
                }

                override fun onError(apiError: ApiError?) {
                    messageData.value = apiError?.getErrorMessage()
                    Log.i(TAG, "error local : ${apiError?.getErrorMessage()}")
                    showProgressbar.value = false
                }
            },
        )
    }

    private fun deletePostsLocal() {
        showProgressbar.value = true
        deletePostLocalUseCase.invoke(
            viewModelScope, null,
            object : UseCaseResponse<Unit> {
                override fun onSuccess(result: Unit) {

                }

                override fun onError(apiError: ApiError?) {
                    messageData.value = apiError?.getErrorMessage()
                    Log.i(TAG, "error local delete : ${apiError?.getErrorMessage()}")
                    showProgressbar.value = false
                }
            },
        )
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    companion object {
        private val TAG = PostsViewModel::class.java.name
    }

}