package com.crafter.internal

import com.crafter.Modrinth
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlin.jvm.Throws
import kotlin.reflect.KProperty

internal open class Route(val path: String, val method: HttpMethod) {
    internal object Project : Route("/project", HttpMethod.Get) {
        val SEARCH = Route("/search", HttpMethod.Get)
        val GET = Route("$path/%s", HttpMethod.Get)
        val DELETE = Route("$path/%s", HttpMethod.Delete)
        val MODIFY = Route("$path/%s", HttpMethod.Patch)
        val MULTIPLE = Route("${path}s", HttpMethod.Get)
        val BULK_EDIT = Route("${path}s", HttpMethod.Patch)

        // random_projects never returns requested number of projects
        // See: https://github.com/modrinth/code/issues/2658
        val RANDOM = Route("${path}s_random", HttpMethod.Get)
    }

    internal fun compile(vararg arguments: String): Route =
        Route(this.path.format(*arguments), this.method)

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String = path

    @Throws(IllegalStateException::class)
    internal suspend fun send(
        body: Any? = null,
        parameters: Map<String, List<String>>? = null
    ): HttpResponse {
        val response = Modrinth.request(this.method, "${Modrinth.ENVIRONMENT}$path", body, parameters)

        if (response.status != HttpStatusCode.OK)
            throw IllegalStateException("Received ${response.status} response: $response")

        return response
    }
}