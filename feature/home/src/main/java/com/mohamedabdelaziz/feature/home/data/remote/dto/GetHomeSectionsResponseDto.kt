package com.mohamedabdelaziz.feature.home.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
internal data class GetHomeSectionsResponseDto(
    @SerializedName("pagination")
    val pagination: PaginationDto? = null,
    @SerializedName("sections")
    val sections: List<HomeSectionDto?>? = null
)
@Serializable
internal data class HomeContentDto(
    // ======= Shared Fields =======
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("author_name")
    val authorName: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @SerializedName("duration")
    val duration: Long? = null,

    @SerializedName("language")
    val language: String? = null,

    @SerializedName("release_date")
    val releaseDate: String? = null,

    @SerializedName("score")
    val score: Double? = null,

    // ======= Article-specific =======
    @SerializedName("article_id")
    val articleId: String? = null,

    // ======= Audiobook-specific =======
    @SerializedName("audiobook_id")
    val audiobookId: String? = null,

    // ======= Podcast Episode-specific =======
    @SerializedName("episode_id")
    val episodeId: String? = null,

    @SerializedName("episode_type")
    val episodeType: String? = null,

    @SerializedName("season_number")
    val seasonNumber: Int? = null,

    @SerializedName("number")
    val number: Int? = null,

    @SerializedName("audio_url")
    val audioUrl: String? = null,

    @SerializedName("separated_audio_url")
    val separatedAudioUrl: String? = null,

    @SerializedName("chapters")
    val chapters: List<String?>? = null,

    // ======= Podcast Show-specific =======
    @SerializedName("podcast_id")
    val podcastId: String? = null,

    @SerializedName("podcast_name")
    val podcastName: String? = null,

    @SerializedName("podcastPopularityScore")
    val podcastPopularityScore: Int? = null,

    @SerializedName("podcastPriority")
    val podcastPriority: Int? = null,

    @SerializedName("episode_count")
    val episodeCount: Int? = null,

    @SerializedName("popularityScore")
    val popularityScore: Int? = null,

    @SerializedName("priority")
    val priority: Int? = null,

    // ======= Paid/Early Access =======
    @SerializedName("paid_is_early_access")
    val paidIsEarlyAccess: Boolean? = null,

    @SerializedName("paid_is_now_early_access")
    val paidIsNowEarlyAccess: Boolean? = null,

    @SerializedName("paid_is_exclusive")
    val paidIsExclusive: Boolean? = null,

    @SerializedName("paid_is_exclusive_partially")
    val paidIsExclusivePartially: Boolean? = null,

    @SerializedName("paid_exclusive_start_time")
    val paidExclusiveStartTime: Int? = null,

    @SerializedName("paid_exclusivity_type")
    val paidExclusivityType: String? = null,

    @SerializedName("paid_early_access_date")
    val paidEarlyAccessDate: String? = null,

    @SerializedName("paid_early_access_audio_url")
    val paidEarlyAccessAudioUrl: String? = null,

    @SerializedName("paid_transcript_url")
    val paidTranscriptUrl: String? = null,

    @SerializedName("free_transcript_url")
    val freeTranscriptUrl: String? = null
)
@Serializable
internal data class HomeSectionDto(
    @SerializedName("content")
    val content: List<HomeContentDto?>? = null,
    @SerializedName("content_type")
    val contentType: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("order")
    val order: Int? = null,
    @SerializedName("type")
    val type: String? = null
)
@Serializable
internal data class PaginationDto(
    @SerializedName("next_page")
    val nextPage: String? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null
)