package com.mohamedabdelaziz.feature.home.presentation.data

import androidx.compose.runtime.Immutable

@Immutable
data class UISearchSectionsResponse(
    val sections: List<UISearchSection>
)

@Immutable
data class UISearchSection(
    val content: List<UISearchContent>,
    val contentType: String,
    val name: String,
    val order: Int,
    val type: String
)

@Immutable
data class UISearchContent(
    val name: String,
    val description: String,
    val avatarUrl: String,
    val duration: Long,
    val language: String,
    val score: Double,
    val episodeCount: Int,
    val podcastId: String,
    val popularityScore: Int,
    val priority: Int
)