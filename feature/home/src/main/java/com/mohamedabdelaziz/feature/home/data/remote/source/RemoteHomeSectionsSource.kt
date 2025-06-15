package com.mohamedabdelaziz.feature.home.data.remote.source

 import com.mohamedabdelaziz.core.network.common.NetworkResult
 import com.mohamedabdelaziz.feature.home.data.remote.dto.GetHomeSectionsResponseDto

/**
 * @author: Mohamed Abdelaziz
 * @since Fri, 13 Jun 2025
 */
internal interface RemoteHomeSectionsSource {
    suspend fun getHomeSections(page: Int): NetworkResult<GetHomeSectionsResponseDto>
}
