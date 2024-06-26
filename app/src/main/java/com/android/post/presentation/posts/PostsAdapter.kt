package com.android.post.presentation.posts

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.android.post.R
import com.android.post.databinding.HolderPostBinding
import com.android.post.domain.model.Post
import com.android.post.utils.removeHtml
import kotlin.properties.Delegates

@SuppressLint("NotifyDataSetChanged")
class PostsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mPostList: List<Post> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    var onClickListener: ((Post) -> Unit?)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderPostBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_post, parent, false
        )
        return PostViewHolder(holderPostBinding)
    }

    override fun getItemCount(): Int = if (mPostList.isNullOrEmpty()) 0 else mPostList.size

    private fun getItem(position: Int): Post = mPostList[position]

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostViewHolder).onBind(getItem(position))
    }

    private inner class PostViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun onBind(post: Post) {
            post.bodyText = post.body.removeHtml() ?: ""
            (viewDataBinding as HolderPostBinding).post = post

            viewDataBinding.root.setOnClickListener {
                onClickListener?.invoke(post)
            }
        }
    }
}