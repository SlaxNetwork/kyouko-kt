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
        /**
         * Create a [GameProfileView] with populated specific game data.
         * This is primarily useful for serving API requests that will be requesting
         * data for specific games, so we can easily send back exactly what we need and no more.
         *
         * @param associatedGameProfile the [KClass] of the game profile to populate.
         * @param rowData the raw data needed to create an instance of the specific game data.
         */
        fun populated(
            associatedGameProfile: KClass<*>,
            rowData: RowData
        ): GameProfileView {
            // constructor of the provided game profile type (ex: cookie clicker)
            val constructor = associatedGameProfile.constructors.firstOrNull { it.hasAnnotation<RowDataConstructor>() }
                ?: throw RuntimeException("no row data constructor found")

            // constructed game profile, example CookieClickerProfile
            val gameProfile = constructor.call(rowData)

            // turns game profile into a view, example CookieClickerProfileView
            val gameProfileView = gameProfile::class.members
                // find the method to serialize into a view.
                .firstOrNull { it.hasAnnotation<ViewSerializerMethod>() }
                ?.call(gameProfile)
                ?: throw RuntimeException("no view serializer method found")

            val inst = GameProfileView()

            val property = GameProfileView::class.declaredMemberProperties
                .firstOrNull {
                    it.instanceParameter?.type?.classifier?.javaClass
                        ?.isInstance(gameProfileView::class)
                        ?: false
                }?.apply { isAccessible = true }
                ?: throw NullPointerException("no property for ${gameProfileView::class.simpleName} exists")

            if(property is KMutableProperty1<GameProfileView, *>) {
                // finally set the needed property on GameProfileView to the game data view.
                (property as KMutableProperty1<GameProfileView, Any>)
                    .set(inst, gameProfileView)
            }

            return inst
        }
    }
}