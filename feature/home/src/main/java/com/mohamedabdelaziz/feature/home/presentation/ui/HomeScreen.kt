package com.mohamedabdelaziz.feature.home.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.mohamedabdelaziz.feature.home.presentation.ui.composables.HomeSectionsLazyColumn
import com.mohamedabdelaziz.feature.home.presentation.ui.composables.SearchBarSection
import com.mohamedabdelaziz.feature.home.presentation.ui.composables.SectionsTabs
import com.mohamedabdelaziz.feature.home.presentation.ui.manipulator.HomeScreenViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

/**
 * @author: Mohamed Abdelaziz
 * @since Sat, 14 Jun 2025
 */

@FlowPreview
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val lazyPagingSectionsItems = viewModel.sections.collectAsLazyPagingItems()
    val homeSectionState by viewModel.state.collectAsState()
    val sections = lazyPagingSectionsItems.itemSnapshotList.items

    val homeScrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val firstVisibleItemIndex by remember {
        derivedStateOf { homeScrollState.firstVisibleItemIndex }
    }

    val loadState by remember(lazyPagingSectionsItems.loadState) { derivedStateOf { lazyPagingSectionsItems.loadState } }
    val isLoading by remember(loadState) {
        derivedStateOf {
            loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading
        }
    }
    LaunchedEffect(Unit) {
        snapshotFlow { query }
            .debounce(200)
            .distinctUntilChanged()
            .collectLatest { debouncedQuery ->
                viewModel.searchSections(debouncedQuery)
            }
    }


    Scaffold(
        topBar = {
            Column {
                if (!isLoading) {
                    SearchBarSection(
                        query = query,
                        onQueryChange = { query = it },
                        onSearch = { viewModel.searchSections(query) },
                        active = active,
                        onActiveChange = { active = it },
                        searchResults = homeSectionState.uiSearchSectionsResponse?.sections.orEmpty()
                    )
                }
                if (sections.isNotEmpty()) {
                    SectionsTabs(
                        categories = sections,
                        firstVisibleIndexFromList = firstVisibleItemIndex,
                        onCategorySelected = { index ->
                            scope.launch { homeScrollState.animateScrollToItem(index) }
                        }
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black
    ) { innerPadding ->
        HomeSectionsLazyColumn(
            sections = sections,
            lazyPagingSectionsItems = lazyPagingSectionsItems,
            homeScreenState = homeSectionState,
            modifier = Modifier,
            scrollState = homeScrollState,
            innerPadding = innerPadding,
        )
    }
}
