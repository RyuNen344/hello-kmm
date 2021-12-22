package com.ryunen344.hello.kmm.db

import com.squareup.sqldelight.ColumnAdapter
import kotlinx.datetime.Instant

class InstantColumnAdapter : ColumnAdapter<Instant, String> {
    override fun decode(databaseValue : String) : Instant {
        return Instant.parse(databaseValue)
    }

    override fun encode(value : Instant) : String {
        return value.toString()
    }
}
