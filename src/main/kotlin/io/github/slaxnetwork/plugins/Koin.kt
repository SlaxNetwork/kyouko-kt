package io.github.slaxnetwork.plugins

import io.github.slaxnetwork.Environment
import io.github.slaxnetwork.database.impl.postgres.PostgresDatabase
import io.github.slaxnetwork.database.impl.postgres.repositories.PostgresGameProfileRepository
import io.github.slaxnetwork.database.impl.postgres.repositories.PostgresProfilePreferencesRepository
import io.github.slaxnetwork.database.impl.postgres.repositories.PostgresProfileRepository
import io.github.slaxnetwork.database.impl.postgres.repositories.PostgresRanksRepository
import io.github.slaxnetwork.database.impl.postgres.repositories.game.PostgresCookieClickerRepository
import io.github.slaxnetwork.database.repositories.GameProfileRepository
import io.github.slaxnetwork.database.repositories.ProfilePreferencesRepository
import io.github.slaxnetwork.database.repositories.ProfileRepository
import io.github.slaxnetwork.database.repositories.RanksRepository
import io.github.slaxnetwork.database.repositories.game.CookieClickerRepository
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureKoin(env: Environment) {
    install(Koin) {

        modules(
            environmentModule(env),
            databaseModule
        )
    }
}

private fun environmentModule(env: Environment) = module {
    single { env }
}

private val databaseModule = module {
    single { PostgresDatabase.create(get()) }

    single<CookieClickerRepository> { PostgresCookieClickerRepository(get()) }
    single<GameProfileRepository> { PostgresGameProfileRepository(get()) }
    single<ProfilePreferencesRepository> { PostgresProfilePreferencesRepository(get()) }
    single<ProfileRepository> { PostgresProfileRepository(get(), get(), get()) }
    single<RanksRepository> { PostgresRanksRepository(get()) }
}