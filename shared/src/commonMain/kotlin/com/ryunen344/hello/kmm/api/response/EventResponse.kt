package com.ryunen344.hello.kmm.api.response

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventResponse(
    /**
     * イベントID
     */
    @SerialName("event_id")
    val eventId : Int,

    /**
     * タイトル
     */
    @SerialName("title")
    val title : String,

    /**
     * キャッチ
     */
    @SerialName("catch")
    val catchStr : String,

    /**
     * 概要(HTML形式)
     */
    @SerialName("description")
    val description : String,

    /**
     * connpass.com 上のURL
     */
    @SerialName("event_url")
    val eventUrl : String,

    /**
     * Twitterのハッシュタグ
     */
    @SerialName("hash_tag")
    val hashTag : String,

    /**
     * イベント開催日時 (ISO-8601形式)
     */
    @SerialName("started_at")
    val startedAt : Instant,

    /**
     * イベント終了日時 (ISO-8601形式)
     */
    @SerialName("ended_at")
    val endedAt : Instant,

    /**
     * 定員
     */
    @SerialName("limit")
    val limit : Int? = null,

    /**
     * イベント参加タイプ
     */
    @SerialName("event_type")
    val eventType : String,

    /**
     * グループ
     */
    @SerialName("series")
    val series : SeriesResponse?,

    /**
     * 開催場所
     */
    @SerialName("address")
    val address : String? = null,

    /**
     * 開催会場
     */
    @SerialName("place")
    val place : String? = null,

    /**
     * 開催会場の緯度
     */
    @SerialName("lat")
    val lat : Float? = null,

    /**
     * 開催会場の経度
     */
    @SerialName("lon")
    val lon : Float? = null,

    /**
     * 管理者のID
     */
    @SerialName("owner_id")
    val ownerId : Int,

    /**
     * 管理者のニックネーム
     */
    @SerialName("owner_nickname")
    val ownerNickname : String,

    /**
     * 管理者の表示名
     */
    @SerialName("owner_display_name")
    val ownerDisplayName : String,

    /**
     * 参加者数
     */
    @SerialName("accepted")
    val accepted : Int,

    /**
     * 補欠者数
     */
    @SerialName("waiting")
    val waiting : Int,

    /**
     * 更新日時 (ISO-8601形式)
     */
    @SerialName("updated_at")
    val updatedAt : Instant
)
