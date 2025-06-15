package com.mohamedabdelaziz.feature.home.domain.repository

import com.mohamedabdelaziz.core.network.common.NetworkResult
import com.mohamedabdelaziz.feature.home.domain.model.DomainSearchSectionsResponse
import kotlinx.coroutines.flow.Flow

/**
 * @author: Mohamed Abdelaziz
 * @since Fri, 13 Jun 2025
 */
interface SearchSectionsRepository {
    suspend fun search(query: String): Flow<NetworkResult<DomainSearchSectionsResponse>>
}