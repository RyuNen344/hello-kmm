package com.ryunen344.hello.kmm.api.impl

import com.ryunen344.hello.kmm.api.ConnpassApi
import com.ryunen344.hello.kmm.api.response.EventsResponse
import io.ktor.client.*
import io.ktor.client.request.*

class ConnpassApiImpl(private val httpClient : HttpClient) : ConnpassApi {
    override suspend fun events() : EventsResponse {
        return httpClient.get("https://connpass.com/api/v1/event")
    }
}
