package com.mohamedabdelaziz.feature.home.presentation.ui.manipulator

import androidx.paging.PagingData
import app.cash.turbine.test
import com.mohamedabdelaziz.core.network.common.NetworkResult
import com.mohamedabdelaziz.feature.home.domain.model.DomainSearchContent
import com.mohamedabdelaziz.feature.home.domain.model.DomainSearchSection
import com.mohamedabdelaziz.feature.home.domain.model.DomainSearchSectionsResponse
import com.mohamedabdelaziz.feature.home.domain.repository.HomeSectionsRepository
import com.mohamedabdelaziz.feature.home.domain.repository.SearchSectionsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeScreenViewModelTest {

    private lateinit var viewModel: HomeScreenViewModel
    private val homeSectionsRepository: HomeSectionsRepository = mockk()
    private val searchSectionsRepository: SearchSectionsRepository = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        coEvery { homeSectionsRepository.getHomeSections() } returns flowOf(PagingData.empty())
        viewModel = HomeScreenViewModel(homeSectionsRepository, searchSectionsRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial load updates state with loading then not loading`() = runTest {
        viewModel.state.test {
            val first = awaitItem()
            assertFalse(first.isLoading)

            val second = awaitItem()
            assertTrue(second.isLoading)

            val third = awaitItem()
            assertFalse(third.isLoading)
        }
    }

    @Test
    fun `search success updates state with results`() = runTest {
        val domainSearchResponse = DomainSearchSectionsResponse(
            sections = listOf(
                DomainSearchSection(
                    content = listOf(
                        DomainSearchContent(
                            name = "Podcast Episode 1",
                            description = "An interesting episode",
                            avatarUrl = "http://example.com/img.jpg",
                            duration = 1234L,
                            language = "en",
                            score = 4.8,
                            episodeCount = 10,
                            podcastId = "podcast123",
                            popularityScore = 90,
                            priority = 1
                        )
                    ),
                    contentType = "podcast",
                    name = "Popular Podcasts",
                    order = 1,
                    type = "queue"
                )
            )
        )

        val fakeResult = NetworkResult.Success(domainSearchResponse)

        coEvery { searchSectionsRepository.search("test") } returns flowOf(fakeResult)

        viewModel.searchSections("test")

        viewModel.state.test {
            skipItems(1) // skip initial state
            val loading = awaitItem()
            assertTrue(loading.isLoading)

            val success = awaitItem()
            assertFalse(success.isLoading)
            assertNotNull(success.uiSearchSectionsResponse)
            assertNull(success.errorMessage)
        }
    }

    @Test
    fun `search error updates state with error message`() = runTest {
        val errorMessage = "Something went wrong"
        coEvery { searchSectionsRepository.search("test") } returns flow {
            emit(NetworkResult.Error(errorMessage))
        }

        viewModel.searchSections("test")

        viewModel.state.test {
            skipItems(1)
            val loading = awaitItem()
            assertTrue(loading.isLoading)

            val error = awaitItem()
            assertFalse(error.isLoading)
            assertEquals(errorMessage, error.errorMessage)
        }
    }
}