package com.android.post.domain.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Post(
    var id: Int,
    var title: String,
    var body: String,
    var bodyText: String = ""
) : Parcelable