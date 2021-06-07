package com.thelonelyastronaut.kmweather

import com.thelonelyastronaut.kmweather.di.commonModule
import com.thelonelyastronaut.kmweather.utils.Logger
import com.thelonelyastronaut.kmweather.utils.LoggerImpl
import com.thelonelyastronaut.kmweather.utils.database.SharedDatabase
import kotlinx.coroutines.runBlocking
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

internal val androidDI = DI {
    import(commonModule)
    bind<Logger>() with singleton { LoggerImpl }
}

object DIContainerAndroid: DIContainer(androidDI) {
    init {
        val database: SharedDatabase by androidDI.instance<SharedDatabase>()

        runBlocking {
            database.initDatabase()
        }
    }
}