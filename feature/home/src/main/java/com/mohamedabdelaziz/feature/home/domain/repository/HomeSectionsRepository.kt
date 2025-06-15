package com.mohamedabdelaziz.feature.home.domain.repository

import androidx.paging.PagingData
import com.mohamedabdelaziz.feature.home.domain.model.DomainHomeSection
import kotlinx.coroutines.flow.Flow

/**
 * @author: Mohamed Abdelaziz
 * @since Fri, 13 Jun 2025
 */
  interface HomeSectionsRepository {
    suspend fun getHomeSections(): Flow<PagingData<DomainHomeSection>>
}