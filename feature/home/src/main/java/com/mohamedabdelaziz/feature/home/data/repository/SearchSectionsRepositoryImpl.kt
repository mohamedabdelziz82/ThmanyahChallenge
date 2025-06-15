package com.mohamedabdelaziz.feature.home.data.repository

import com.mohamedabdelaziz.core.network.common.NetworkResult
import com.mohamedabdelaziz.feature.home.data.remote.source.RemoteSearchSectionsSource
import com.mohamedabdelaziz.feature.home.domain.mapper.toDomain
import com.mohamedabdelaziz.feature.home.domain.model.DomainSearchSectionsResponse
import com.mohamedabdelaziz.feature.home.domain.repository.SearchSectionsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * @author: Mohamed Abdelaziz
 * @since Fri, 13 Jun 2025
 */
internal class SearchSectionsRepositoryImpl @Inject constructor(
    private val remoteSearchSectionsSource: RemoteSearchSectionsSource,
    private val ioDispatcher: CoroutineDispatcher
) : SearchSectionsRepository {

    override suspend fun search(query: String): Flow<NetworkResult<DomainSearchSectionsResponse>> = flow {
        emit(NetworkResult.Loading)
        when (val result = remoteSearchSectionsSource.search(query)) {
            is NetworkResult.Success -> {
                emit(NetworkResult.Success(result.data.toDomain()))
            }

            is NetworkResult.Error -> {
                emit(NetworkResult.Error(result.message, result.code))
            }

            NetworkResult.Loading -> {
                emit(NetworkResult.Loading)
            }
        }

    }.flowOn(ioDispatcher).catch { throwable ->
        emit(NetworkResult.Error(throwable.message ?: "Unknown error"))
    }
}
