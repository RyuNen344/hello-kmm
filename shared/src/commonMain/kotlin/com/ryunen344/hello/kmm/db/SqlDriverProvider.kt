package com.ryunen344.hello.kmm.db

import com.squareup.sqldelight.db.SqlDriver

interface SqlDriverProvider {
    fun provideSqlDriver() : SqlDriver
}

const val databaseFileName : String = "connpass.db"
