package com.mohamedabdelaziz.feature.home.domain.model

internal data class DomainHomeSectionsResponse(
    val pagination: DomainPagination,
    val sections: List<DomainHomeSection>
)

sealed class DomainHomeContent {
    abstract val name: String
    abstract val description: String
    abstract val avatarUrl: String
    abstract val duration: Long
    abstract val language: String
    abstract val releaseDate: String
    abstract val score: Double

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
    ) : DomainHomeContent()

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
    ) : DomainHomeContent()

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
    ) : DomainHomeContent()

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
    ) : DomainHomeContent()
}


data class DomainHomeSection(
    val content: List<DomainHomeContent>,
    val contentType: String,
    val name: String,
    val order: Int,
    val type: String
)

internal data class DomainPagination(
    val nextPage: String,
    val totalPages: Int
)
