package com.mohamedabdelaziz.feature.home.data.source

import com.mohamedabdelaziz.core.network.common.NetworkResult
import com.mohamedabdelaziz.feature.home.data.remote.dto.GetHomeSectionsResponseDto
import com.mohamedabdelaziz.feature.home.data.remote.dto.PaginationDto
import com.mohamedabdelaziz.feature.home.data.remote.source.HomeSectionsPagingSource
import com.mohamedabdelaziz.feature.home.data.remote.source.RemoteHomeSectionsSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HomeSectionsPagingSourceTest {

    private lateinit var remoteHomeSectionsSource: RemoteHomeSectionsSource
    private lateinit var pagingSource: HomeSectionsPagingSource

    @Before
    fun setup() {
        remoteHomeSectionsSource = mockk()
        pagingSource = HomeSectionsPagingSource(remoteHomeSectionsSource)
    }

    @Test
    fun `fetchPage should call remote source and return result`() = runTest {
        val page = 1
        val fakeResponse = NetworkResult.Success(
            GetHomeSectionsResponseDto(
                sections = listOf(/* ... */),
                pagination = PaginationDto(/* ... */)
            )
        )

        coEvery { remoteHomeSectionsSource.getHomeSections(page) } returns fakeResponse

        val result = pagingSource.fetchPage(page)

        coVerify(exactly = 1) { remoteHomeSectionsSource.getHomeSections(page) }

        assertEquals(fakeResponse, result)
    }
}
