package com.ryunen344.hello.kmm.db.impl

import com.ryunen344.hello.kmm.db.ConnpassDatabase
import com.ryunen344.hello.kmm.db.SqlDriverProvider
import com.ryunen344.hello.kmm.db.databaseFileName
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.squareup.sqldelight.logs.LogSqliteDriver

actual class SqlDriverProviderImpl : SqlDriverProvider {
    actual override fun provideSqlDriver() : SqlDriver {
        return LogSqliteDriver(
            NativeSqliteDriver(ConnpassDatabase.Schema, databaseFileName),
            ::println
        )
    }
}
