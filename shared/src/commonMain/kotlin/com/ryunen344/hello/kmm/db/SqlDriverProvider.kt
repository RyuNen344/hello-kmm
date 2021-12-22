package com.ryunen344.hello.kmm.db

import com.ryunen344.hello.kmm.repository.EventEntity
import com.ryunen344.hello.kmm.repository.SeriesEntity
import com.squareup.sqldelight.db.SqlDriver

interface SqlDriverProvider {
    fun provideSqlDriver() : SqlDriver
}

const val databaseFileName : String = "connpass.db"

fun List<EventEntity>.toEventRecords() : List<Event> {
    return map { it.toRecord() }
}

fun List<SeriesEntity>.toSeriesRecords() : List<Series> {
    return map { it.toRecord() }
}

fun EventEntity.toRecord() : Event {
    return Event(
        event_id = eventId,
        title = title,
        catchStr = catchStr,
        description = description,
        event_url = eventUrl,
        hash_tag = hashTag,
        started_at = startedAt,
        ended_at = endedAt,
        participant_limit = limit,
        event_type = eventType,
        series_id = series?.id,
        address = address,
        place = place,
        lat = lat,
        lon = lon,
        owner_id = ownerId,
        owner_nickname = ownerNickname,
        owner_display_name = ownerDisplayName,
        accepted = accepted,
        waiting = waiting,
        updated_at = updatedAt
    )
}

fun SeriesEntity.toRecord() : Series {
    return Series(
        id = id,
        title = title,
        url = url
    )
}

fun List<Event>.toEntity(seriesEntity : List<SeriesEntity>) : List<EventEntity> {
    return map { event ->
        val series = event.series_id?.let { seriesId -> seriesEntity.find { it.id == seriesId } }
        event.toEntity(series)
    }
}

fun Event.toEntity(series : SeriesEntity? = null) : EventEntity {
    return EventEntity(
        eventId = event_id,
        title = title,
        catchStr = catchStr,
        description = description,
        eventUrl = event_url,
        hashTag = hash_tag,
        startedAt = started_at,
        endedAt = ended_at,
        limit = participant_limit,
        eventType = event_type,
        series = series,
        address = address,
        place = place,
        lat = lat,
        lon = lon,
        ownerId = owner_id,
        ownerNickname = owner_nickname,
        ownerDisplayName = owner_display_name,
        accepted = accepted,
        waiting = waiting,
        updatedAt = updated_at
    )
}

fun Series.toEntity() : SeriesEntity {
    return SeriesEntity(
        id = id,
        title = title,
        url = url
    )
}
