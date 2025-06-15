package com.mohamedabdelaziz.feature.home.presentation.data

import androidx.compose.runtime.Immutable

@Immutable
data class UIHomeSectionsResponse(
    val pagination: UIPagination,
    val sections: List<UIHomeSection>
)
@Immutable
sealed class UIHomeContent {
    abstract val name: String
    abstract val description: String
    abstract val avatarUrl: String
    abstract val duration: Long
    abstract val language: String
    abstract val releaseDate: String
    abstract val score: Double
    @Immutable
    data class Article(
        override val name: String,
        override val description: String,
        override val avatarUrl: String,
        override val duration: Long,
        override val language: String,
        override val releaseDate: String,
        override val score: Double,
        val authorName: String,
        val articleId: String
    ) : UIHomeContent()

    @Immutable
    data class Audiobook(
        override val name: String,
        override val description: String,
        override val avatarUrl: String,
        override val duration: Long,
        override val language: String,
        override val releaseDate: String,
        override val score: Double,
        val authorName: String,
        val audiobookId: String
    ) : UIHomeContent()

    @Immutable
    data class PodcastEpisode(
        override val name: String,
        override val description: String,
        override val avatarUrl: String,
        override val duration: Long,
        override val language: String,
        override val releaseDate: String,
        override val score: Double,
        val authorName: String,
        val episodeId: String,
        val episodeType: String,
        val seasonNumber: Int,
        val number: Int,
        val audioUrl: String,
        val separatedAudioUrl: String,
        val chapters: List<String>,
        val podcastId: String,
        val podcastName: String,
        val paidIsEarlyAccess: Boolean,
        val paidIsNowEarlyAccess: Boolean,
        val paidIsExclusive: Boolean,
        val paidIsExclusivePartially: Boolean,
        val paidExclusiveStartTime: Int,
        val paidExclusivityType: String,
        val paidEarlyAccessDate: String,
        val paidEarlyAccessAudioUrl: String,
        val paidTranscriptUrl: String,
        val freeTranscriptUrl: String
    ) : UIHomeContent()

    @Immutable
    data class PodcastShow(
        override val name: String,
        override val description: String,
        override val avatarUrl: String,
        override val duration: Long,
        override val language: String,
        override val releaseDate: String,
        override val score: Double,
        val authorName: String,
        val podcastId: String,
        val podcastName: String,
        val episodeCount: Int,
        val podcastPopularityScore: Int,
        val podcastPriority: Int,
        val popularityScore: Int,
        val priority: Int
    ) : UIHomeContent()
}

@Immutable
data class UIHomeSection(
    val content: List<UIHomeContent>,
    val contentType: ContentType,
    val name: String,
    val order: Int,
    val layoutType: LayoutType
)

@Immutable
data class UIPagination(
    val nextPage: String,
    val totalPages: Int
)

@Immutable
enum class ContentType {
    PODCAST, EPISODE, AUDIOBOOK, ARTICLE, UNKNOWN;
    companion object {
        fun fromString(value: String): ContentType {
            return when (value.lowercase()) {
                "podcast" -> PODCAST
                "episode" -> EPISODE
                "audio_book" -> AUDIOBOOK
                "audio_article" -> ARTICLE
                else -> UNKNOWN
            }
        }
    }
}

@Immutable
enum class LayoutType {
    SQUARE,
    BIG_SQUARE,
    TWO_LINE_GRID,
    QUEUE,
    UNKNOWN;
    companion object {
        fun fromString(value: String): LayoutType {
            return when (value.lowercase()) {
                "square" -> SQUARE
                "big_square", "big square" -> BIG_SQUARE
                "2_lines_grid" -> TWO_LINE_GRID
                "queue" -> QUEUE
                else -> UNKNOWN
            }
        }
    }
}
