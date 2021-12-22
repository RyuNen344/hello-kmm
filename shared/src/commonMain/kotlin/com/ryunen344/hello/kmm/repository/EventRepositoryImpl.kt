package com.ryunen344.hello.kmm.repository

import com.ryunen344.hello.kmm.api.ConnpassApi
import com.ryunen344.hello.kmm.api.toEntity
import com.ryunen344.hello.kmm.db.ConnpassDatabase
import com.ryunen344.hello.kmm.db.impl.EventQueriesDelegate
import com.ryunen344.hello.kmm.db.toEntity
import com.ryunen344.hello.kmm.db.toEventRecords
import com.ryunen344.hello.kmm.db.toSeriesRecords
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class EventRepositoryImpl(
    private val api : ConnpassApi,
    private val database : ConnpassDatabase
) : EventRepository {

    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val _events : MutableStateFlow<List<EventEntity>> = MutableStateFlow(emptyList())

    override val events : StateFlow<List<EventEntity>>
        get() = _events

    private val delegate = EventQueriesDelegate(database)

    init {
        database.eventQueries.selectAllEvent().asFlow().mapToList().onEach {
            val series =
                database.eventQueries.selectAllSeries().executeAsList().map { it.toEntity() }
            _events.value = it.toEntity(series)
        }.launchIn(scope)
    }

    override suspend fun refresh() {
        runCatching {
            val response = api.events()
            val events = response.toEntity()

            val seriesRecords = events.mapNotNull { it.series }.toSeriesRecords()
            val eventRecords = events.toEventRecords()

            database.transaction {
                seriesRecords.forEach {
                    delegate.upsertSeries(it)
                }
                eventRecords.forEach {
                    delegate.upsertEvent(it)
                }
            }
        }.onFailure {
            println(it)
        }
    }
}
