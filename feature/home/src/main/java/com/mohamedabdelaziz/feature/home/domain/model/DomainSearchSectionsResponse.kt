package com.mohamedabdelaziz.feature.home.domain.model

data class DomainSearchSectionsResponse(
    val sections: List<DomainSearchSection>
)

data class DomainSearchSection(
    val content: List<DomainSearchContent>,
    val contentType: String,
    val name: String,
    val order: Int,
    val type: String
)


data class DomainSearchContent(
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
