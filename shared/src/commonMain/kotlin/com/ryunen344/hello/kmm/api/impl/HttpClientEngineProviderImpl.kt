package com.ryunen344.hello.kmm.api.impl

import com.ryunen344.hello.kmm.api.HttpClientEngineProvider
import io.ktor.client.engine.*

expect class HttpClientEngineProviderImpl : HttpClientEngineProvider {
    override fun provideHttpClientEngineProvider() : HttpClientEngineFactory<*>
}
