package com.crafter

import com.crafter.internal.Environment
import com.crafter.internal.Route
import com.crafter.models.Project
import com.crafter.models.ProjectSearchResult
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

public object Modrinth {
    public val INSTANCE: ModrinthBuilder = modrinth

    internal const val ENVIRONMENT = Environment.PRODUCTION

    private val client = HttpClient(CIO) {
        install(ContentNegotiation)
    }

    internal suspend fun request(
        method: HttpMethod,
        url: String,
        body: Any?,
        parameters: Map<String, List<String>>?,
    ): HttpResponse = client.request(url) {
        this.method = method

        contentType(ContentType.Application.Json)

        if (body != null) setBody(body)

        url {
            parameters?.forEach { (key, value) -> this.parameters.appendAll(key, value) }
        }
    }
}