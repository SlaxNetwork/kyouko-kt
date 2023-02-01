package io.github.slaxnetwork

import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.DotenvException
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.properties.decodeFromStringMap

@Serializable
data class Environment(
    @SerialName("PORT")
    val port: Int,

    @SerialName("DB_URL")
    val dbUrl: String
) {
    companion object {
        fun loadEnvironment(): Environment {
            val map = try {
                val dotenv = Dotenv.load()

                dotenv.entries()
                    .associateBy { it.key }
                    .mapValues { it.value.value }
            } catch(ex: Exception) {
                System.getenv()
            }

            @OptIn(ExperimentalSerializationApi::class)
            return Properties.decodeFromStringMap(map)
        }
    }
}
