package com.mohamedabdelaziz.feature.home.presentation.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.mohamedabdelaziz.feature.home.presentation.data.ContentType
import com.mohamedabdelaziz.feature.home.presentation.data.LayoutType
import com.mohamedabdelaziz.feature.home.presentation.data.UIHomeSection
import com.mohamedabdelaziz.feature.home.presentation.ui.manipulator.HomeScreenState

@Composable
internal fun HomeSectionsLazyColumn(
    sections: List<UIHomeSection>,
    lazyPagingSectionsItems: LazyPagingItems<UIHomeSection>,
    homeScreenState: HomeScreenState,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState(),
    innerPadding: PaddingValues = PaddingValues(0.dp)
) {
    val loadState = lazyPagingSectionsItems.loadState
    val isLoading =
        loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading ||
                homeScreenState.isLoading

    val error = (loadState.refresh as? LoadState.Error)?.error
        ?: (loadState.append as? LoadState.Error)?.error

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding),
        state = scrollState
    ) {
        itemsIndexed(
            items = sections,
            key = { index, section ->
                "${section.name}_${section.contentType.name}_$index"
            }
        ) { _, section ->
            SectionTitle(title = section.name)

            when (section.layoutType to section.contentType) {
                LayoutType.SQUARE to ContentType.PODCAST -> SquareSection(section)
                LayoutType.QUEUE to ContentType.PODCAST -> QueueSection(section)
                LayoutType.SQUARE to ContentType.ARTICLE -> SquareSection(section)
                LayoutType.BIG_SQUARE to ContentType.EPISODE -> BigSquareSection(section)
                LayoutType.TWO_LINE_GRID to ContentType.EPISODE -> TwoLineGridSection(section)
                LayoutType.TWO_LINE_GRID to ContentType.AUDIOBOOK -> TwoLineGridSection(section)
                LayoutType.BIG_SQUARE to ContentType.AUDIOBOOK -> BigSquareSection(section)
            }
        }



        if (error != null || !homeScreenState.errorMessage.isNullOrEmpty()) {
            item {
                Text(
                    text = "Error: ${homeScreenState.errorMessage ?: error?.localizedMessage ?: "حدث خطأ"}",
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
    if (isLoading) {
             Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
    }
}