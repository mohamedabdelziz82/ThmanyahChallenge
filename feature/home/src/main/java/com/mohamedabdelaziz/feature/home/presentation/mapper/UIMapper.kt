package com.mohamedabdelaziz.feature.home.presentation.mapper

import com.mohamedabdelaziz.core.network.utils.toRelativeTimeText
import com.mohamedabdelaziz.feature.home.domain.model.DomainHomeContent
import com.mohamedabdelaziz.feature.home.domain.model.DomainHomeSection
import com.mohamedabdelaziz.feature.home.domain.model.DomainSearchContent
import com.mohamedabdelaziz.feature.home.domain.model.DomainSearchSection
import com.mohamedabdelaziz.feature.home.domain.model.DomainSearchSectionsResponse
import com.mohamedabdelaziz.feature.home.presentation.data.ContentType
import com.mohamedabdelaziz.feature.home.presentation.data.LayoutType
import com.mohamedabdelaziz.feature.home.presentation.data.UIHomeContent
import com.mohamedabdelaziz.feature.home.presentation.data.UIHomeSection
import com.mohamedabdelaziz.feature.home.presentation.data.UISearchContent
import com.mohamedabdelaziz.feature.home.presentation.data.UISearchSection
import com.mohamedabdelaziz.feature.home.presentation.data.UISearchSectionsResponse

/**
 * @author: Mohamed Abdelaziz
 * @since Sat, 14 Jun 2025
 */

internal fun DomainHomeSection.toUI(): UIHomeSection {
    return UIHomeSection(
        content = content.map { it.toUI() },
        contentType = ContentType.fromString(contentType),
        name = name,
        order = order,
        layoutType = LayoutType.fromString(type)
    )
}

internal fun DomainHomeContent.toUI(): UIHomeContent {
    return when (this) {
        is DomainHomeContent.Article -> UIHomeContent.Article(
            name = name,
            description = description,
            avatarUrl = avatarUrl,
            duration = duration,
            language = language,
            releaseDate =  releaseDate.toRelativeTimeText(),
            score = score,
            authorName = authorName,
            articleId = articleId
        )

        is DomainHomeContent.Audiobook -> UIHomeContent.Audiobook(
            name = name,
            description = description,
            avatarUrl = avatarUrl,
            duration = duration,
            language = language,
            releaseDate =  releaseDate.toRelativeTimeText(),
            score = score,
            authorName = authorName,
            audiobookId = audiobookId
        )

        is DomainHomeContent.PodcastEpisode -> UIHomeContent.PodcastEpisode(
            name = name,
            description = description,
            avatarUrl = avatarUrl,
            duration = duration,
            language = language,
            releaseDate =  releaseDate.toRelativeTimeText(),
            score = score,
            authorName = authorName,
            episodeId = episodeId,
            episodeType = episodeType,
            seasonNumber = seasonNumber,
            number = number,
            audioUrl = audioUrl,
            separatedAudioUrl = separatedAudioUrl,
            chapters = chapters,
            podcastId = podcastId,
            podcastName = podcastName,
            paidIsEarlyAccess = paidIsEarlyAccess,
            paidIsNowEarlyAccess = paidIsNowEarlyAccess,
            paidIsExclusive = paidIsExclusive,
            paidIsExclusivePartially = paidIsExclusivePartially,
            paidExclusiveStartTime = paidExclusiveStartTime,
            paidExclusivityType = paidExclusivityType,
            paidEarlyAccessDate = paidEarlyAccessDate,
            paidEarlyAccessAudioUrl = paidEarlyAccessAudioUrl,
            paidTranscriptUrl = paidTranscriptUrl,
            freeTranscriptUrl = freeTranscriptUrl
        )

        is DomainHomeContent.PodcastShow -> UIHomeContent.PodcastShow(
            name = name,
            description = description,
            avatarUrl = avatarUrl,
            duration = duration,
            language = language,
            releaseDate = releaseDate.toRelativeTimeText(),
            score = score,
            authorName = authorName,
            podcastId = podcastId,
            podcastName = podcastName,
            episodeCount = episodeCount,
            podcastPopularityScore = podcastPopularityScore,
            podcastPriority = podcastPriority,
            popularityScore = popularityScore,
            priority = priority
        )
    }

}
internal fun DomainSearchSectionsResponse.toUI(): UISearchSectionsResponse {
    return UISearchSectionsResponse(
        sections = sections.map { it.toUI() }
    )
}

internal fun DomainSearchSection.toUI(): UISearchSection {
    return UISearchSection(
        content = content.map { it.toUI() },
        contentType = contentType,
        name = name,
        order = order,
        type = type
    )
}

internal fun DomainSearchContent.toUI(): UISearchContent {
    return UISearchContent(
        name = name,
        description = description,
        avatarUrl = avatarUrl,
        duration = duration,
        language = language,
        score = score,
        episodeCount = episodeCount,
        podcastId = podcastId,
        popularityScore = popularityScore,
        priority = priority
    )
}
fun UISearchSection.toUIHomeSection(): UIHomeSection {
    return UIHomeSection(
        content = content.map { it.toUIHomeContent() },
        contentType = ContentType.fromString(contentType),
        name = name,
        order = order,
        layoutType = LayoutType.fromString(type)
    )
}
fun UISearchContent.toUIHomeContent(): UIHomeContent{
    return  UIHomeContent.PodcastShow(
        name = name,
        description = description,
        avatarUrl = avatarUrl,
        duration = duration,
        language = language,
        score = score,
        episodeCount = episodeCount,
        podcastId = podcastId,
        popularityScore = popularityScore,
        priority = priority,
        releaseDate = "",
        authorName = "",
        podcastName = "",
        podcastPriority = 0,
        podcastPopularityScore = 0
    )
}




