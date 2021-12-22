package com.ryunen344.hello.kmm.api.impl

import com.ryunen344.hello.kmm.api.ConnpassApi
import com.ryunen344.hello.kmm.api.HttpClientEngineProvider
import com.ryunen344.hello.kmm.api.provideHttpClient
import io.ktor.client.engine.*
import io.ktor.client.engine.ios.*

actual class HttpClientEngineProviderImpl : HttpClientEngineProvider {
    actual override fun provideHttpClientEngineProvider() : HttpClientEngineFactory<*> = Ios
}

fun provideApi() : ConnpassApi = ConnpassApiImpl(provideHttpClient(HttpClientEngineProviderImpl()))
