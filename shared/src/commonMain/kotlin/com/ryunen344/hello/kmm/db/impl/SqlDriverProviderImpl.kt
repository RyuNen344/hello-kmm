package com.ryunen344.hello.kmm.db.impl

import com.ryunen344.hello.kmm.db.SqlDriverProvider
import com.squareup.sqldelight.db.SqlDriver

expect class SqlDriverProviderImpl : SqlDriverProvider {
    override fun provideSqlDriver() : SqlDriver
}
