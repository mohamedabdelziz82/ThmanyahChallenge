package com.mohamedabdelaziz.feature.home.presentation.ui.composables
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.mohamedabdelaziz.feature.home.presentation.data.UISearchSection
import com.mohamedabdelaziz.feature.home.presentation.mapper.toUIHomeSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchBarSection(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    searchResults: List<UISearchSection>,
) {
    SearchBar(
        modifier = Modifier.testTag("SearchBar"),
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = active,
        onActiveChange = onActiveChange,
        placeholder = { Text("Search...") },
    ) {
        if (query.isNotBlank()) {
            LazyColumn(
                modifier = Modifier
                    .background(Color.Black)
                    .testTag("SearchResultsList")
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(searchResults) { searchSection ->
                    SearchItemRow(searchSection.toUIHomeSection())
                }
            }
        }
    }
}
