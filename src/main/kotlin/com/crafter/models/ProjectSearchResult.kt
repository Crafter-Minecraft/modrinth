package com.crafter.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ProjectSearchResult(
    val hits: List<ProjectHitResult>,
    val offset: Int,
    val limit: Int,
    @SerialName("total_hits") val totalHits: Int
)