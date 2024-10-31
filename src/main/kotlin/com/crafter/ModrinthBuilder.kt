package com.crafter

import com.crafter.internal.Route
import com.crafter.models.Project
import com.crafter.models.ProjectSearchResult
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json

public object ModrinthBuilder {
    private val json = Json { ignoreUnknownKeys = true }

    public lateinit var token: String
    public val project: Project = Project

    public object Project {
        public suspend fun get(identifier: String): com.crafter.models.Project =
            deserialize<com.crafter.models.Project>(
                Route.Project.GET.compile(identifier)
                    .send()
                    .bodyAsText()
            )

        public suspend fun search(identifier: String): ProjectSearchResult =
            deserialize<ProjectSearchResult>(
                Route.Project.SEARCH
                    .send(null, mapOf("query" to listOf(identifier)))
                    .bodyAsText()
            )

        public suspend fun random(count: Int): List<com.crafter.models.Project> =
            deserialize(
                Route.Project.RANDOM
                    .send(null, mapOf("count" to listOf(count.toString())))
                    .bodyAsText()
            )
    }

    internal inline fun <reified T> deserialize(jsonData: String): T =
        json.decodeFromString<T>(jsonData)

    internal fun token(provided: String?): ModrinthBuilder = apply { token = provided ?: "" }
}

public suspend fun <T> modrinth(
    token: String? = null,
    block: suspend ModrinthBuilder.() -> T
): T = block(ModrinthBuilder.token(token))

public fun modrinth(
    token: String? = null
): ModrinthBuilder = ModrinthBuilder.token(token)

public val modrinth: ModrinthBuilder = modrinth()
