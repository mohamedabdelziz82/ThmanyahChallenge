package com.mohamedabdelaziz.feature.home.domain.mapper

import com.mohamedabdelaziz.feature.home.data.remote.dto.GetSearchSectionsResponseDto
import com.mohamedabdelaziz.feature.home.data.remote.dto.HomeContentDto
import com.mohamedabdelaziz.feature.home.data.remote.dto.HomeSectionDto
import com.mohamedabdelaziz.feature.home.data.remote.dto.SearchContentDto
import com.mohamedabdelaziz.feature.home.data.remote.dto.SearchSectionDto
import com.mohamedabdelaziz.feature.home.domain.model.DomainHomeContent
import com.mohamedabdelaziz.feature.home.domain.model.DomainHomeSection
import com.mohamedabdelaziz.feature.home.domain.model.DomainSearchContent
import com.mohamedabdelaziz.feature.home.domain.model.DomainSearchSection
import com.mohamedabdelaziz.feature.home.domain.model.DomainSearchSectionsResponse

/**
 * @author: Mohamed Abdelaziz
 * @since Fri, 13 Jun 2025
 */



internal fun HomeSectionDto.toDomain(): DomainHomeSection {
    return DomainHomeSection(
        content = content.orEmpty().filterNotNull().map { it.toDomain() },
        contentType = contentType.orEmpty(),
        name = name.orEmpty(),
        order = order ?: 0,
        type = type.orEmpty()
    )
}

internal fun HomeContentDto.toDomain(): DomainHomeContent {
    return when {
        !articleId.isNullOrBlank() -> DomainHomeContent.Article(
            name = name.orEmpty(),
            description = description.orEmpty(),
            avatarUrl = avatarUrl.orEmpty(),
            duration = duration ?: 0,
            language = language.orEmpty(),
            releaseDate = releaseDate.orEmpty(),
            score = score ?: 0.0,
            authorName = authorName.orEmpty(),
            articleId = articleId
        )
        !audiobookId.isNullOrBlank() -> DomainHomeContent.Audiobook(
            name = name.orEmpty(),
            description = description.orEmpty(),
            avatarUrl = avatarUrl.orEmpty(),
            duration = duration ?: 0,
            language = language.orEmpty(),
            releaseDate = releaseDate.orEmpty(),
            score = score ?: 0.0,
            authorName = authorName.orEmpty(),
            audiobookId = audiobookId
        )
        !episodeId.isNullOrBlank() -> DomainHomeContent.PodcastEpisode(
            name = name.orEmpty(),
            description = description.orEmpty(),
            avatarUrl = avatarUrl.orEmpty(),
            duration = duration ?: 0,
            language = language.orEmpty(),
            releaseDate = releaseDate.orEmpty(),
            score = score ?: 0.0,
            authorName = authorName.orEmpty(),
            episodeId = episodeId,
            episodeType = episodeType.orEmpty(),
            seasonNumber = seasonNumber ?: 0,
            number = number ?: 0,
            audioUrl = audioUrl.orEmpty(),
            separatedAudioUrl = separatedAudioUrl.orEmpty(),
            chapters = chapters.orEmpty().filterNotNull(),
            podcastId = podcastId.orEmpty(),
            podcastName = podcastName.orEmpty(),
            paidIsEarlyAccess = paidIsEarlyAccess ?: false,
            paidIsNowEarlyAccess = paidIsNowEarlyAccess ?: false,
            paidIsExclusive = paidIsExclusive ?: false,
            paidIsExclusivePartially = paidIsExclusivePartially ?: false,
            paidExclusiveStartTime = paidExclusiveStartTime ?: 0,
            paidExclusivityType = paidExclusivityType.orEmpty(),
            paidEarlyAccessDate = paidEarlyAccessDate.orEmpty(),
            paidEarlyAccessAudioUrl = paidEarlyAccessAudioUrl.orEmpty(),
            paidTranscriptUrl = paidTranscriptUrl.orEmpty(),
            freeTranscriptUrl = freeTranscriptUrl.orEmpty()
        )
        !podcastId.isNullOrBlank() -> DomainHomeContent.PodcastShow(
            name = name.orEmpty(),
            description = description.orEmpty(),
            avatarUrl = avatarUrl.orEmpty(),
            duration = duration ?: 0,
            language = language.orEmpty(),
            releaseDate = releaseDate.orEmpty(),
            score = score ?: 0.0,
            authorName = authorName.orEmpty(),
            podcastId = podcastId,
            podcastName = podcastName.orEmpty(),
            episodeCount = episodeCount ?: 0,
            podcastPopularityScore = podcastPopularityScore ?: 0,
            podcastPriority = podcastPriority ?: 0,
            popularityScore = popularityScore ?: 0,
            priority = priority ?: 0
        )
        else -> throw IllegalArgumentException("Unknown content type: $this")
    }
}



internal fun GetSearchSectionsResponseDto.toDomain(): DomainSearchSectionsResponse {
    return DomainSearchSectionsResponse(
        sections = this.sections.orEmpty()
            .filterNotNull()
            .map { it.toDomain() }
    )
}

internal fun SearchSectionDto.toDomain(): DomainSearchSection {
    return DomainSearchSection(
        content = this.content.orEmpty()
            .filterNotNull()
            .map { it.toDomain() },
        contentType = contentType.orEmpty(),
        name = name.orEmpty(),
        order = order?.toIntOrNull() ?: 0,
        type = type.orEmpty()
    )
}

internal fun SearchContentDto.toDomain(): DomainSearchContent {
    return DomainSearchContent(
        name = name.orEmpty(),
        description = description.orEmpty(),
        avatarUrl = avatarUrl.orEmpty(),
        duration = duration?.toLongOrNull() ?: 0L,
        language = language.orEmpty(),
        score = score?.toDoubleOrNull() ?: 0.0,
        episodeCount = episodeCount?.toIntOrNull() ?: 0,
        podcastId = podcastId.orEmpty(),
        popularityScore = popularityScore?.toIntOrNull() ?: 0,
        priority = priority?.toIntOrNull() ?: 0
    )
}
