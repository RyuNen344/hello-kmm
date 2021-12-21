package com.ryunen344.hello.kmm.db.impl

import com.ryunen344.hello.kmm.db.ConnpassDatabase
import com.ryunen344.hello.kmm.db.Event
import com.ryunen344.hello.kmm.db.Series

class EventQueriesDelegate(private val connpassDatabase : ConnpassDatabase) {

    fun selectAllEvent() : List<Event> {
        return connpassDatabase.eventQueries.selectAllEvent().executeAsList()
    }

    fun selectAllSeries() : List<Series> {
        return connpassDatabase.eventQueries.selectAllSeries().executeAsList()
    }

    fun upsertEvent(event : Event) {
        connpassDatabase.eventQueries.upsertEvent(
            event.title,
            event.catchStr,
            event.description,
            event.event_url,
            event.hash_tag,
            event.started_at,
            event.ended_at,
            event.participant_limit,
            event.event_type,
            event.series_id,
            event.address,
            event.place,
            event.lat,
            event.lon,
            event.owner_id,
            event.owner_nickname,
            event.owner_display_name,
            event.accepted,
            event.waiting,
            event.updated_at,
            event.event_id,
        )
    }

    fun upsertSeries(series : Series) {
        connpassDatabase.eventQueries.upsertSeries(series.title, series.url, series.id)
    }
}
