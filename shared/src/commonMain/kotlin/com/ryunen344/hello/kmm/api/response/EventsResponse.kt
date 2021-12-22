package com.ryunen344.hello.kmm.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventsResponse(
    /**
     * 含まれる検索結果の件数
     */
    @SerialName("results_returned")
    val resultsReturned : Int,

    /**
     * 検索結果の総件数
     */
    @SerialName("results_available")
    val resultsAvailable : Int,

    /**
     * 検索の開始位置
     */
    @SerialName("results_start")
    val resultsStart : Int,

    /**
     * 検索結果のイベントリスト
     */
    @SerialName("events")
    val events : List<EventResponse>
)
