package com.ryunen344.hello.kmm.db.impl

import android.content.Context
import com.ryunen344.hello.kmm.db.ConnpassDatabase
import com.ryunen344.hello.kmm.db.SqlDriverProvider
import com.ryunen344.hello.kmm.db.databaseFileName
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.logs.LogSqliteDriver

actual class SqlDriverProviderImpl(private val context : Context) : SqlDriverProvider {
    actual override fun provideSqlDriver() : SqlDriver {
        return LogSqliteDriver(
            AndroidSqliteDriver(
                ConnpassDatabase.Schema,
                context,
                databaseFileName
            ), ::println
        )
    }
}
