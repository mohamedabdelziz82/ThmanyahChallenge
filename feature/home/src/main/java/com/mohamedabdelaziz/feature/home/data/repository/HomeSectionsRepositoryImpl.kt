package com.mohamedabdelaziz.feature.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mohamedabdelaziz.feature.home.common.Constants.PAGE_SIZE
import com.mohamedabdelaziz.feature.home.data.remote.source.HomeSectionsPagingSource
import com.mohamedabdelaziz.feature.home.data.remote.source.RemoteHomeSectionsSource
import com.mohamedabdelaziz.feature.home.domain.model.DomainHomeSection
import com.mohamedabdelaziz.feature.home.domain.repository.HomeSectionsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * @author: Mohamed Abdelaziz
 * @since Fri, 13 Jun 2025
 */
internal class HomeSectionsRepositoryImpl @Inject constructor(
    private val remoteHomeSectionsSource: RemoteHomeSectionsSource,
    private val ioDispatcher: CoroutineDispatcher
) : HomeSectionsRepository {
    override suspend fun getHomeSections(): Flow<PagingData<DomainHomeSection>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = PAGE_SIZE,
                prefetchDistance = 1
            ),
            pagingSourceFactory = {
                HomeSectionsPagingSource(remoteHomeSectionsSource)
            }
        ).flow.flowOn(ioDispatcher)
    }
}
