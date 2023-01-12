package io.github.slaxnetwork.database.impl.postgres.utils

import com.github.jasync.sql.db.QueryResult
import com.github.jasync.sql.db.SuspendingConnection
import org.intellij.lang.annotations.Language

suspend fun SuspendingConnection.execute(@Language("SQL") query: String): QueryResult {
    return execute(query, listOf())
}

suspend fun SuspendingConnection.execute(@Language("SQL") query: String, args: List<Any?>): QueryResult {
    return sendPreparedStatement(query, args)
}

suspend fun SuspendingConnection.execute(@Language("SQL") query: String, vararg args: Any?): QueryResult {
    return execute(query, args.toList())
}