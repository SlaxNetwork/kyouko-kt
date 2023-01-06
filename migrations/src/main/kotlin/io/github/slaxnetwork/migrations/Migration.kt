package io.github.slaxnetwork.migrations

import org.litote.kmongo.coroutine.CoroutineDatabase

abstract class Migration(
    val id: String
) {
     abstract suspend fun run(db: CoroutineDatabase)

     abstract suspend fun revert(db: CoroutineDatabase)
}