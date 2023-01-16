package io.github.slaxnetwork.plugins

import io.github.slaxnetwork.database.impl.postgres.PostgresDatabase
import io.github.slaxnetwork.database.impl.postgres.repositories.PostgresGameProfileRepository
import io.github.slaxnetwork.database.impl.postgres.repositories.PostgresProfileRepository
import io.github.slaxnetwork.database.impl.postgres.repositories.PostgresRanksRepository
import io.github.slaxnetwork.database.impl.postgres.repositories.game.PostgresCookieClickerRepository
import io.github.slaxnetwork.database.repositories.GameProfileRepository
import io.github.slaxnetwork.database.repositories.ProfileRepository
import io.github.slaxnetwork.database.repositories.RanksRepository
import io.github.slaxnetwork.database.repositories.game.CookieClickerRepository
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

const val DB_NAME = "slaxnetwork"

fun Application.configureKoin() {
    install(Koin) {
        modules(
            databaseModule
        )
    }
}

private val databaseModule = module {
    single {
        PostgresDatabase.create()

    }

    single<CookieClickerRepository> { PostgresCookieClickerRepository(get()) }
    single<GameProfileRepository> { PostgresGameProfileRepository(get(), get()) }
    single<ProfileRepository> { PostgresProfileRepository(get(), get()) }
    single<RanksRepository> { PostgresRanksRepository(get()) }
}