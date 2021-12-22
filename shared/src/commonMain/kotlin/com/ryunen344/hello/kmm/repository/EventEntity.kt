package com.ryunen344.hello.kmm.repository

import kotlinx.datetime.Instant

data class EventEntity(
    val eventId : Int,
    val title : String,
    val catchStr : String,
    val description : String,
    val eventUrl : String,
    val hashTag : String,
    val startedAt : Instant,
    val endedAt : Instant,
    val limit : Int? = null,
    val eventType : String,
    val series : SeriesEntity?,
    val address : String? = null,
    val place : String? = null,
    val lat : Float? = null,
    val lon : Float? = null,
    val ownerId : Int,
    val ownerNickname : String,
    val ownerDisplayName : String,
    val accepted : Int,
    val waiting : Int,
    val updatedAt : Instant
)
