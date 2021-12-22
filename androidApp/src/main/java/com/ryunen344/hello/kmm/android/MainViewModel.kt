package com.ryunen344.hello.kmm.android

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ryunen344.hello.kmm.api.impl.ConnpassApiImpl
import com.ryunen344.hello.kmm.api.impl.HttpClientEngineProviderImpl
import com.ryunen344.hello.kmm.api.provideHttpClient
import com.ryunen344.hello.kmm.db.impl.SqlDriverProviderImpl
import com.ryunen344.hello.kmm.db.provideDatabase
import com.ryunen344.hello.kmm.repository.EventEntity
import com.ryunen344.hello.kmm.repository.EventRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application : Application) : AndroidViewModel(application) {
    private val connpassApi =
        ConnpassApiImpl(httpClient = provideHttpClient(HttpClientEngineProviderImpl()))

    private val connpassDatabase = provideDatabase(SqlDriverProviderImpl(application))

    private val repository = EventRepositoryImpl(connpassApi, connpassDatabase)

    val events : StateFlow<List<EventEntity>>
        get() = repository.events

    fun fetchEvents() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                runCatching {
                    repository.refresh()
                }.onFailure {
                    println(it)
                }
            }
        }
    }
}
