package io.github.slaxnetwork.migrations.impl.profile

import io.github.slaxnetwork.migrations.Migration
import org.litote.kmongo.coroutine.CoroutineDatabase

object TestProfileMigration : Migration("example") {
    override suspend fun run(db: CoroutineDatabase) {
        TODO("Not yet implemented")
    }

    override suspend fun revert(db: CoroutineDatabase) {
        TODO("Not yet implemented")
    }
}