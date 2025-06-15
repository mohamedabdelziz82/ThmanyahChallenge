package com.mohamedabdelaziz.feature.home.data.source

import com.mohamedabdelaziz.core.network.common.NetworkResult
import com.mohamedabdelaziz.feature.home.core.api.HomeApiService
import com.mohamedabdelaziz.feature.home.data.remote.dto.GetSearchSectionsResponseDto
import com.mohamedabdelaziz.feature.home.data.remote.source.RemoteSearchSectionsSourceImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RemoteSearchSectionsSourceImplTest {

    private lateinit var apiService: HomeApiService
    private lateinit var remoteSource: RemoteSearchSectionsSourceImpl

    @Before
    fun setup() {
        apiService = mockk()
        remoteSource = RemoteSearchSectionsSourceImpl(apiService)
    }

    @Test
    fun `search should call apiService and return success result`() = runTest {
        val query = "pizza"
        val fakeDto = GetSearchSectionsResponseDto()
        val fakeResponse = Response.success(fakeDto)

        coEvery { apiService.search(query) } returns fakeResponse

        val result = remoteSource.search(query)

        coVerify(exactly = 1) { apiService.search(query) }

        assertTrue(result is NetworkResult.Success)
    }
}
