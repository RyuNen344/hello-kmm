package com.ryunen344.hello.kmm.api

import com.ryunen344.hello.kmm.api.response.EventsResponse

interface ConnpassApi {
    suspend fun events() : EventsResponse
}
