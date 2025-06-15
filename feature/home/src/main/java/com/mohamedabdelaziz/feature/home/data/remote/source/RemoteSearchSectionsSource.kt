package com.mohamedabdelaziz.feature.home.data.remote.source

 import com.mohamedabdelaziz.core.network.common.NetworkResult
 import com.mohamedabdelaziz.feature.home.data.remote.dto.GetSearchSectionsResponseDto

/**
 * @author: Mohamed Abdelaziz
 * @since Fri, 13 Jun 2025
 */
internal interface RemoteSearchSectionsSource {
    suspend fun search(query: String): NetworkResult<GetSearchSectionsResponseDto>
}
