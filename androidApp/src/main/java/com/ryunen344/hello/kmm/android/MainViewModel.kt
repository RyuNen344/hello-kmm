package com.ryunen344.hello.kmm.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryunen344.hello.kmm.api.impl.ConnpassApiImpl
import com.ryunen344.hello.kmm.api.impl.HttpClientEngineProviderImpl
import com.ryunen344.hello.kmm.api.provideHttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    private val connpassApi =
        ConnpassApiImpl(httpClient = provideHttpClient(HttpClientEngineProviderImpl()))

    fun fetchEvents() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                runCatching {
                    connpassApi.events()
                }.onFailure {
                    println(it)
                }
            }
        }
    }
}
