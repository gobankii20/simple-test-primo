package com.android.post.domain.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
class PostResponseWrapper @JvmOverloads constructor(
    @field: Element(name = "channel")
    var channel: PostResponse? = null
)

@Root(name = "channel", strict = false)
class PostResponse @JvmOverloads constructor(
    @field: ElementList(inline = true)
    var itemList: List<PostItem>? = null
)

@Root(name = "item", strict = false)
class PostItem @JvmOverloads constructor(
    @field: Element(name = "title")
    var title: String = "",
    @Path("content")
    @field: Element(name = "encoded", required = false)
    var content: String = "",
    @field: Element(name = "pubDate")
    var pubDate: String = "",
    @Path("atom")
    @field: Element(name = "updated", required = false)
    var atomUpdated: String = "",
    @field: Element(name = "link")
    var link: String = "",
)