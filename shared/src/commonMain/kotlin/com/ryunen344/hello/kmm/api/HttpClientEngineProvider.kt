package com.ryunen344.hello.kmm.api

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface HttpClientEngineProvider {
    fun provideHttpClientEngineProvider() : HttpClientEngineFactory<*>
}

fun provideHttpClient(httpClientEngineProvider : HttpClientEngineProvider) : HttpClient {
    return HttpClient(httpClientEngineProvider.provideHttpClientEngineProvider()) {
        Json {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
            })
        }
        Logging {
            logger = object : Logger {
                override fun log(message : String) {
                    println(message)
                }
            }
        }
    }
}
