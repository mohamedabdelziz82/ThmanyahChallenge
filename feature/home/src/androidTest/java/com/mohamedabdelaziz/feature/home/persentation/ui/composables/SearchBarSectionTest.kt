package com.mohamedabdelaziz.feature.home.persentation.ui.composables

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import com.mohamedabdelaziz.feature.home.presentation.data.UISearchSection
import com.mohamedabdelaziz.feature.home.presentation.ui.composables.SearchBarSection
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SearchBarSectionTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }
    private val dummyResults = listOf(
        UISearchSection(
            content = emptyList(),
            contentType = "TypeA",
            name = "Section1",
            order = 1,
            type = "Type1"
        ),
        UISearchSection(
            content = emptyList(),
            contentType = "TypeB",
            name = "Section2",
            order = 2,
            type = "Type2"
        )
    )

    @Test
    fun searchBar_initialState_placeholderIsDisplayed() {
        composeTestRule.setContent {
            SearchBarSection(
                query = "",
                onQueryChange = {},
                onSearch = {},
                active = false,
                onActiveChange = {},
                searchResults = emptyList()
            )
        }

        composeTestRule.onNodeWithTag("SearchPlaceholder")
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals("Search...")
    }

    @Test
    fun searchBar_withQuery_displaysSearchResults() {
        composeTestRule.setContent {
            SearchBarSection(
                query = "query",
                onQueryChange = {},
                onSearch = {},
                active = true,
                onActiveChange = {},
                searchResults = dummyResults
            )
        }

        composeTestRule.onNodeWithTag("SearchResultsList")
            .assertExists()
            .assertIsDisplayed()

        dummyResults.forEach { result ->
            composeTestRule.onNodeWithTag("SearchResultItem_${result.name}")
                .assertExists()
                .assertIsDisplayed()
                .assertTextContains(result.name)
        }
    }

    @Test
    fun searchBar_onQueryChangeCallbackIsTriggered() {
        var capturedQuery = ""
        composeTestRule.setContent {
            SearchBarSection(
                query = "",
                onQueryChange = { capturedQuery = it },
                onSearch = {},
                active = false,
                onActiveChange = {},
                searchResults = emptyList()
            )
        }

        composeTestRule.onNodeWithTag("SearchBar")
            .performTextInput("test")

        assert(capturedQuery == "test")
    }
}

@AndroidEntryPoint
class HiltTestActivity : ComponentActivity()
