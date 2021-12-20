package com.ryunen344.hello.kmm.api.impl

import com.ryunen344.hello.kmm.api.HttpClientEngineProvider
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*

actual class HttpClientEngineProviderImpl : HttpClientEngineProvider {
    actual override fun provideHttpClientEngineProvider() : HttpClientEngineFactory<*> = OkHttp
}
