package com.mohamedabdelaziz.feature.home.data.repository

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingSource
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.mohamedabdelaziz.feature.home.data.remote.source.HomeSectionsPagingSource
import com.mohamedabdelaziz.feature.home.data.remote.source.RemoteHomeSectionsSource
import com.mohamedabdelaziz.feature.home.domain.model.DomainHomeContent
import com.mohamedabdelaziz.feature.home.domain.model.DomainHomeSection
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkConstructor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeSectionsRepositoryImplTest {

    private val remoteSource: RemoteHomeSectionsSource = mockk()
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: HomeSectionsRepositoryImpl

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = HomeSectionsRepositoryImpl(remoteSource, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getHomeSections should return paging data flow from remote source`() = runTest {
        // Arrange
        val fakeContent = listOf(
            DomainHomeContent.Article(
                name = "Article 1",
                description = "Desc",
                avatarUrl = "url",
                duration = 123L,
                language = "ar",
                releaseDate = "2023-01-01",
                score = 4.5,
                authorName = "Author A",
                articleId = "A1"
            )
        )
        val fakeSection = DomainHomeSection(
            content = fakeContent,
            contentType = "article",
            name = "Section 1",
            order = 0,
            type = "carousel"
        )

        mockkConstructor(HomeSectionsPagingSource::class)
        val mockPagingSource = mockk<HomeSectionsPagingSource>()

        coEvery { mockPagingSource.load(any()) } returns PagingSource.LoadResult.Page(
            data = listOf(fakeSection),
            prevKey = null,
            nextKey = null
        )

        // Act
        val flow = repository.getHomeSections()

        // Assert
        val differ = AsyncPagingDataDiffer(
            diffCallback = object : DiffUtil.ItemCallback<DomainHomeSection>() {
                override fun areItemsTheSame(
                    oldItem: DomainHomeSection,
                    newItem: DomainHomeSection
                ) = oldItem.name == newItem.name

                override fun areContentsTheSame(
                    oldItem: DomainHomeSection,
                    newItem: DomainHomeSection
                ) = oldItem == newItem
            },
            updateCallback = NoopListCallback(),
            workerDispatcher = testDispatcher
        )

        val job = launch { flow.collectLatest { differ.submitData(it) } }
        advanceUntilIdle()

        val result = differ.snapshot().items
        assertEquals(1, result.size)
        assertEquals("Section 1", result[0].name)

        job.cancel()
    }

    // No-op callback for testing
    private class NoopListCallback : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) = Unit
        override fun onRemoved(position: Int, count: Int) = Unit
        override fun onMoved(fromPosition: Int, toPosition: Int) = Unit
        override fun onChanged(position: Int, count: Int, payload: Any?) = Unit
    }
}
