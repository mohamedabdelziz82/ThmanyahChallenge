package com.mohamedabdelaziz.feature.home.data.remote.source

import com.mohamedabdelaziz.core.network.common.NetworkResult
import com.mohamedabdelaziz.core.network.common.safeApiCall
import com.mohamedabdelaziz.feature.home.core.api.HomeApiService
import com.mohamedabdelaziz.feature.home.data.remote.dto.GetSearchSectionsResponseDto
import javax.inject.Inject

/**
 * @author: Mohamed Abdelaziz
 * @since Fri, 13 Jun 2025
 */
internal class RemoteSearchSectionsSourceImpl @Inject constructor(
    private val apiService: HomeApiService
) : RemoteSearchSectionsSource {

    override suspend fun search(query: String): NetworkResult<GetSearchSectionsResponseDto> {
       return safeApiCall {
            apiService.search(query)
        }
    }
}
