package com.mohamedabdelaziz.feature.home.data.remote.source

import com.mohamedabdelaziz.core.network.common.NetworkResult
import com.mohamedabdelaziz.feature.home.data.remote.dto.GetHomeSectionsResponseDto
import javax.inject.Inject

internal class HomeSectionsPagingSource @Inject constructor(
    private val remoteHomeSectionsSource: RemoteHomeSectionsSource
) : BaseSectionsPagingSource() {
    override suspend fun fetchPage(page: Int): NetworkResult<GetHomeSectionsResponseDto> {
        return remoteHomeSectionsSource.getHomeSections(page)
    }
}


