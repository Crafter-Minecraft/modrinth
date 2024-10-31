package com.crafter.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Project(
    @SerialName("project_type") val projectType: String,
    @SerialName("client_side") val clientSide: String,
    @SerialName("server_side") val serverSide: String,
    val description: String,
    val versions: List<String>,
    val gallery: List<GalleryImage>?,
    @SerialName("icon_url") val iconUrl: String? = null,
    @SerialName("discord_url") val discordUrl: String? = null,
    @SerialName("source_url") val sourceUrl: String? = null,
    @SerialName("wiki_url") val wikiUrl: String? = null,
    @SerialName("issues_url") val issuesUrl: String? = null,
    val id: String,
    val slug: String,
    val team: String,
    val published: String,
    val updated: String,
    val approved: String?,
    val queued: String? = null,
    val followers: Int,
    val license: License,
    @SerialName("game_versions") val gameVersions: List<String>?,
    val loaders: List<String>?,
    val color: Int? = null
) {
    val url: String = "https://modrinth.com/$projectType/$slug"
}


@Serializable
public data class GalleryImage(
    val url: String,
    val featured: Boolean,
    val title: String? = null,
    val description: String? = null,
    val created: String,
    val ordering: Int
)

@Serializable
public data class License(
    val id: String,
    val name: String,
    val url: String?
)