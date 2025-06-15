package com.mohamedabdelaziz.feature.home.data.source

import com.mohamedabdelaziz.core.network.common.NetworkResult
import com.mohamedabdelaziz.feature.home.core.api.HomeApiService
import com.mohamedabdelaziz.feature.home.data.remote.dto.GetHomeSectionsResponseDto
import com.mohamedabdelaziz.feature.home.data.remote.source.RemoteHomeSectionsSourceImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RemoteHomeSectionsSourceImplTest {

    private lateinit var apiService: HomeApiService
    private lateinit var remoteSource: RemoteHomeSectionsSourceImpl

    @Before
    fun setup() {
        apiService = mockk()
        remoteSource = RemoteHomeSectionsSourceImpl(apiService)
    }

    @Test
    fun `getHomeSections should call apiService and return success result`() = runTest {
        val page = 1
        val fakeDto = GetHomeSectionsResponseDto()
        val fakeResponse = Response.success(fakeDto)
         coEvery { apiService.getHomeSections(page) } returns fakeResponse

        val result = remoteSource.getHomeSections(page)

        coVerify(exactly = 1) { apiService.getHomeSections(page) }
        assertTrue(result is NetworkResult.Success)
    }
}
