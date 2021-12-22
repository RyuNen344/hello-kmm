package com.ryunen344.hello.kmm.repository

import kotlinx.coroutines.flow.StateFlow

interface EventRepository {
    val events : StateFlow<List<EventEntity>>

    suspend fun refresh()
}
