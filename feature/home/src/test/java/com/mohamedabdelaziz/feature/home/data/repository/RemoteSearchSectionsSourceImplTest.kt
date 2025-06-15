package com.mohamedabdelaziz.feature.home.data.repository

import com.mohamedabdelaziz.core.network.common.NetworkResult
import com.mohamedabdelaziz.feature.home.core.api.HomeApiService
import com.mohamedabdelaziz.feature.home.data.remote.dto.GetSearchSectionsResponseDto
import com.mohamedabdelaziz.feature.home.data.remote.dto.PaginationDto
import com.mohamedabdelaziz.feature.home.data.remote.source.RemoteSearchSectionsSourceImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
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
    fun `search should call apiService and return NetworkResult Success`() = runTest {
        // Arrange
        val query = "pizza"
        val fakeDto = GetSearchSectionsResponseDto(
            sections = listOf(/* بيانات تجريبية */),
        )
        val response = Response.success(fakeDto)

        coEvery { apiService.search(query) } returns response

        // Act
        val result = remoteSource.search(query)

        // Assert
        coVerify(exactly = 1) { apiService.search(query) }
        assertTrue(result is NetworkResult.Success)
    }
    @Test
    fun `search should return NetworkResult Error on failed response`() = runTest {
        val query = "burger"
        val errorResponse = Response.error<GetSearchSectionsResponseDto>(404, "".toResponseBody())

        coEvery { apiService.search(query) } returns errorResponse

        val result = remoteSource.search(query)

        assertTrue(result is NetworkResult.Error)
    }

}
