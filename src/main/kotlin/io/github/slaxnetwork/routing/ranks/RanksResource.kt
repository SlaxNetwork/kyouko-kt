package io.github.slaxnetwork.routing.ranks

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Resource("/ranks") @Serializable
class RanksResource {
    @Resource("{id}") @Serializable
    class Id(
        val parent: RanksResource = RanksResource(),
        val id: String
    ) {
        @Resource("inheritance") @Serializable
        class Inheritance(
            val parent: Id
        )
    }
}