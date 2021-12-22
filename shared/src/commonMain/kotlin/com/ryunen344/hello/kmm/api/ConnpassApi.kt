package com.ryunen344.hello.kmm.api

import com.ryunen344.hello.kmm.api.response.EventResponse
import com.ryunen344.hello.kmm.api.response.EventsResponse
import com.ryunen344.hello.kmm.api.response.SeriesResponse
import com.ryunen344.hello.kmm.repository.EventEntity
import com.ryunen344.hello.kmm.repository.SeriesEntity

interface ConnpassApi {
    suspend fun events() : EventsResponse
}

fun EventsResponse.toEntity() : List<EventEntity> {
    return events.map { it.toEntity() }
}

fun EventResponse.toEntity() : EventEntity {
    return EventEntity(
        eventId = eventId,
        title = title,
        catchStr = catchStr,
        description = description,
        eventUrl = eventUrl,
        hashTag = hashTag,
        startedAt = startedAt,
        endedAt = endedAt,
        limit = limit,
        eventType = eventType,
        series = series?.let { it.toEntity() },
        address = address,
        place = place,
        lat = lat,
        lon = lon,
        ownerId = ownerId,
        ownerNickname = ownerNickname,
        ownerDisplayName = ownerDisplayName,
        accepted = accepted,
        waiting = waiting,
        updatedAt = updatedAt
    )
}

fun SeriesResponse.toEntity() : SeriesEntity {
    return SeriesEntity(
        id = id,
        title = title,
        url = url
    )
}
