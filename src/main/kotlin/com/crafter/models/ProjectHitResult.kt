package com.crafter.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ProjectHitResult(
    @SerialName("project_type") val projectType: String,
    @SerialName("client_side") val clientSide: String,
    @SerialName("server_side") val serverSide: String,
    val description: String,
    val license: String,
    val version: String? = null,
    val gallery: List<String>,
    val slug: String,
    val queued: String? = null,
    val color: Int
) {
    val url: String = "https://modrinth.com/$projectType/$slug"
}
