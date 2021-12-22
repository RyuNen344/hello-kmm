package com.ryunen344.hello.kmm.android

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ryunen344.hello.kmm.api.impl.ConnpassApiImpl
import com.ryunen344.hello.kmm.api.impl.HttpClientEngineProviderImpl
import com.ryunen344.hello.kmm.api.provideHttpClient
import com.ryunen344.hello.kmm.api.toEntity
import com.ryunen344.hello.kmm.db.impl.EventQueriesDelegate
import com.ryunen344.hello.kmm.db.impl.SqlDriverProviderImpl
import com.ryunen344.hello.kmm.db.provideDatabase
import com.ryunen344.hello.kmm.db.toEventRecords
import com.ryunen344.hello.kmm.db.toSeriesRecords
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application : Application) : AndroidViewModel(application) {
    private val connpassApi =
        ConnpassApiImpl(httpClient = provideHttpClient(HttpClientEngineProviderImpl()))

    private val connpassDatabase = provideDatabase(SqlDriverProviderImpl(application))

    fun fetchEvents() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                runCatching {
                    val response = connpassApi.events()
                    val events = response.toEntity()

                    val seriesRecords = events.mapNotNull { it.series }.toSeriesRecords()
                    val eventRecords = events.toEventRecords()

                    val delegate = EventQueriesDelegate(connpassDatabase)

                    connpassDatabase.transaction {
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
    }
}
