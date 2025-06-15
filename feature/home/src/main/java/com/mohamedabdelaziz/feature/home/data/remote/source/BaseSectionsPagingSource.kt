package com.mohamedabdelaziz.feature.home.data.remote.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mohamedabdelaziz.core.network.common.NetworkResult
import com.mohamedabdelaziz.feature.home.common.Constants.FIRST_PAGE
import com.mohamedabdelaziz.feature.home.common.Constants.PAGE_SIZE
import com.mohamedabdelaziz.feature.home.data.remote.dto.GetHomeSectionsResponseDto
import com.mohamedabdelaziz.feature.home.domain.mapper.toDomain
import com.mohamedabdelaziz.feature.home.domain.model.DomainHomeSection

internal abstract class BaseSectionsPagingSource : PagingSource<Int, DomainHomeSection>() {
    abstract suspend fun fetchPage(page: Int): NetworkResult<GetHomeSectionsResponseDto>
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DomainHomeSection> {
        val currentPage = params.key ?: FIRST_PAGE
        return when (val result = fetchPage(currentPage)) {
            is NetworkResult.Success -> {
                val items = result.data.sections
                    ?.filterNotNull()
                    ?.map { it.toDomain() }
                    .orEmpty()

                LoadResult.Page(
                    data = items,
                    prevKey = if (items.size < PAGE_SIZE) null else currentPage  + 1,
                    nextKey = result.data.pagination?.nextPage?.toIntOrNull()
                )
            }

            is NetworkResult.Error -> LoadResult.Error(Exception(result.message))
            is NetworkResult.Loading -> LoadResult.Error(Exception("Still loading"))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DomainHomeSection>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}


