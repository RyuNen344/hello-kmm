package com.ryunen344.hello.kmm.db

fun provideDatabase(sqlDriverProvider : SqlDriverProvider) : ConnpassDatabase {
    val instantColumnAdapter = InstantColumnAdapter()
    return ConnpassDatabase(
        driver = sqlDriverProvider.provideSqlDriver(),
        eventAdapter = Event.Adapter(
            started_atAdapter = instantColumnAdapter,
            ended_atAdapter = instantColumnAdapter,
            updated_atAdapter = instantColumnAdapter
        )
    )
}
