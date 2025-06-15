package com.mohamedabdelaziz.feature.home.data.remote.source

import com.mohamedabdelaziz.core.network.common.NetworkResult
import com.mohamedabdelaziz.core.network.common.safeApiCall
import com.mohamedabdelaziz.feature.home.core.api.HomeApiService
import com.mohamedabdelaziz.feature.home.data.remote.dto.GetHomeSectionsResponseDto
import javax.inject.Inject

/**
 * @author: Mohamed Abdelaziz
 * @since Fri, 13 Jun 2025
 */
internal class RemoteHomeSectionsSourceImpl @Inject constructor(
    private val apiService: HomeApiService
) : RemoteHomeSectionsSource {
    override suspend fun getHomeSections(page: Int): NetworkResult<GetHomeSectionsResponseDto> {
        return safeApiCall {
            apiService.getHomeSections(page)
        }
    }
}
