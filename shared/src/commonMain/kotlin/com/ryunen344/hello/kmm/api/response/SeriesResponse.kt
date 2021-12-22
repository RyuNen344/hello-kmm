package com.ryunen344.hello.kmm.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeriesResponse(
    /**
     * グループID
     */
    @SerialName("id")
    val id : Int,

    /**
     * グループタイトル
     */
    @SerialName("title")
    val title : String,

    /**
     * グループのconnpass.com 上のURL
     */
    @SerialName("url")
    val url : String
)
