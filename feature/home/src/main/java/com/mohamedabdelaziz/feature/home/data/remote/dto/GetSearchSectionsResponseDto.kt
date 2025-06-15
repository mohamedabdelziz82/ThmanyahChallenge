package com.mohamedabdelaziz.feature.home.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GetSearchSectionsResponseDto(
    @SerialName("sections")
    val sections: List<SearchSectionDto?>? = null
)
@Serializable
internal data class SearchSectionDto(
    @SerialName("content")
    val content: List<SearchContentDto?>? = null,
    @SerialName("content_type")
    val contentType: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("order")
    val order: String? = null,
    @SerialName("type")
    val type: String? = null
)
@Serializable
internal data class SearchContentDto(
    @SerialName("avatar_url")
    val avatarUrl: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("duration")
    val duration: String? = null,
    @SerialName("episode_count")
    val episodeCount: String? = null,
    @SerialName("language")
    val language: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("podcast_id")
    val podcastId: String? = null,
    @SerialName("popularityScore")
    val popularityScore: String? = null,
    @SerialName("priority")
    val priority: String? = null,
    @SerialName("score")
    val score: String? = null
)