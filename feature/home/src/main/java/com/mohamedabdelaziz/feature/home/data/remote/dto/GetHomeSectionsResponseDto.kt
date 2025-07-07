package com.mohamedabdelaziz.feature.home.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GetHomeSectionsResponseDto(
    @SerialName("pagination")
    val pagination: PaginationDto? = null,
    @SerialName("sections")
    val sections: List<HomeSectionDto?>? = null
)
@Serializable
internal data class HomeContentDto(
    // ======= Shared Fields =======
    @SerialName("name")
    val name: String? = null,

    @SerialName("author_name")
    val authorName: String? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("avatar_url")
    val avatarUrl: String? = null,

    @SerialName("duration")
    val duration: Long? = null,

    @SerialName("language")
    val language: String? = null,

    @SerialName("release_date")
    val releaseDate: String? = null,

    @SerialName("score")
    val score: Double? = null,

    // ======= Article-specific =======
    @SerialName("article_id")
    val articleId: String? = null,

    // ======= Audiobook-specific =======
    @SerialName("audiobook_id")
    val audiobookId: String? = null,

    // ======= Podcast Episode-specific =======
    @SerialName("episode_id")
    val episodeId: String? = null,

    @SerialName("episode_type")
    val episodeType: String? = null,

    @SerialName("season_number")
    val seasonNumber: Int? = null,

    @SerialName("number")
    val number: Int? = null,

    @SerialName("audio_url")
    val audioUrl: String? = null,

    @SerialName("separated_audio_url")
    val separatedAudioUrl: String? = null,

    @SerialName("chapters")
    val chapters: List<String?>? = null,

    // ======= Podcast Show-specific =======
    @SerialName("podcast_id")
    val podcastId: String? = null,

    @SerialName("podcast_name")
    val podcastName: String? = null,

    @SerialName("podcastPopularityScore")
    val podcastPopularityScore: Int? = null,

    @SerialName("podcastPriority")
    val podcastPriority: Int? = null,

    @SerialName("episode_count")
    val episodeCount: Int? = null,

    @SerialName("popularityScore")
    val popularityScore: Int? = null,

    @SerialName("priority")
    val priority: Int? = null,

    // ======= Paid/Early Access =======
    @SerialName("paid_is_early_access")
    val paidIsEarlyAccess: Boolean? = null,

    @SerialName("paid_is_now_early_access")
    val paidIsNowEarlyAccess: Boolean? = null,

    @SerialName("paid_is_exclusive")
    val paidIsExclusive: Boolean? = null,

    @SerialName("paid_is_exclusive_partially")
    val paidIsExclusivePartially: Boolean? = null,

    @SerialName("paid_exclusive_start_time")
    val paidExclusiveStartTime: Int? = null,

    @SerialName("paid_exclusivity_type")
    val paidExclusivityType: String? = null,

    @SerialName("paid_early_access_date")
    val paidEarlyAccessDate: String? = null,

    @SerialName("paid_early_access_audio_url")
    val paidEarlyAccessAudioUrl: String? = null,

    @SerialName("paid_transcript_url")
    val paidTranscriptUrl: String? = null,

    @SerialName("free_transcript_url")
    val freeTranscriptUrl: String? = null
)
@Serializable
internal data class HomeSectionDto(
    @SerialName("content")
    val content: List<HomeContentDto?>? = null,
    @SerialName("content_type")
    val contentType: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("order")
    val order: Int? = null,
    @SerialName("type")
    val type: String? = null
)
@Serializable
internal data class PaginationDto(
    @SerialName("next_page")
    val nextPage: String? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null
)