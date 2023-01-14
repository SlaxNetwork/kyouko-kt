package io.github.slaxnetwork.api.models.views.profile.game

import com.github.jasync.sql.db.RowData
import io.github.slaxnetwork.api.annotations.RowDataConstructor
import io.github.slaxnetwork.api.annotations.ViewSerializerMethod
import io.ktor.util.reflect.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.reflect.*
import kotlin.reflect.full.*
import kotlin.reflect.jvm.isAccessible

@Serializable
data class GameProfileView(
    @SerialName("cookie_clicker")
    var cookieClicker: CookieClickerProfileView? = null
) {
    companion object {
        fun populated(
            associatedGameProfile: KClass<*>,
            rowData: RowData
        ): GameProfileView {
            // constructor of the provided game profile type (ex: cookie clicker)
            val constructor = associatedGameProfile.constructors.firstOrNull { it.hasAnnotation<RowDataConstructor>() }
                ?: throw RuntimeException("no row data constructor found")

            // epicly constructured o0sdfgkimj game data.
            // need to turn this into a view bruh
            val probablyCookieClicker = constructor.call(rowData)

            val serializedView = probablyCookieClicker::class.members
                .firstOrNull { it.hasAnnotation<ViewSerializerMethod>() }
                ?.call(probablyCookieClicker)
                ?: throw RuntimeException("no view serializer method found")

            val inst = GameProfileView()

            val property = GameProfileView::class.declaredMemberProperties
                .firstOrNull {
                    it.instanceParameter?.type?.classifier?.javaClass
                        ?.isInstance(serializedView::class)
                        ?: false
                }?.apply { isAccessible = true }
                ?: throw NullPointerException("e")

            if(property is KMutableProperty1<GameProfileView, *>) {
                (property as KMutableProperty1<GameProfileView, Any>)
                    .set(inst, serializedView)
            }

            return inst
        }
    }
}