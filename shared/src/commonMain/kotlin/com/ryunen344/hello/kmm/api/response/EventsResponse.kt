package com.ryunen344.hello.kmm.api.response

data class EventsResponse(
    val resultsReturned : Int,
    val resultsAvailable : Int,
    val resultsStart : Int,
    val events : List<EventResponse>
)
