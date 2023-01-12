package io.github.slaxnetwork.plugins

import com.github.jasync.sql.db.SuspendingConnection
import com.mongodb.ConnectionString
import io.github.slaxnetwork.database.impl.mongo.MongoDatabase
import io.github.slaxnetwork.database.impl.mongo.repositories.MongoProfileRepository
import io.github.slaxnetwork.database.impl.mongo.repositories.MongoPunishmentRepository
import io.github.slaxnetwork.database.impl.mongo.repositories.MongoRanksRepository
import io.github.slaxnetwork.database.impl.postgres.PostgresDatabase
import io.github.slaxnetwork.database.impl.postgres.repositories.PostgresRanksRepository
import io.github.slaxnetwork.database.repositories.ProfileRepository
import io.github.slaxnetwork.database.repositories.PunishmentRepository
import io.github.slaxnetwork.database.repositories.RanksRepository
import io.ktor.server.application.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(
            databaseModule
        )
    }
}

private val databaseModule = module {
    single {
        MongoDatabase.create(
            System.getenv("MONGO_DB_NAME") ?: "slaxnetwork",
            ConnectionString(System.getenv("MONGO_DB_CONNECTION_STR") ?: "mongodb://localhost:27017/slaxnetwork")
        )
    }

    single {
        PostgresDatabase.create()
    }

    single<ProfileRepository> { MongoProfileRepository(get()) }
    single<RanksRepository> { PostgresRanksRepository(get()) }
    single<PunishmentRepository> { MongoPunishmentRepository(get(), get()) }
}