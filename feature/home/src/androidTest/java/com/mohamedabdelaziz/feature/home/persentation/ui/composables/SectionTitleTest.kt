package com.mohamedabdelaziz.feature.home.persentation.ui.composables

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.mohamedabdelaziz.feature.home.presentation.ui.composables.SectionTitle
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SectionTitleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun sectionTitle_DisplayedCorrectly() {
        val testTitle = "محتوى مميز"

        composeTestRule.setContent {
            SectionTitle(title = testTitle)
        }

        composeTestRule
            .onNodeWithTag("SectionTitleText")
            .assertExists()
            .assertTextEquals(testTitle)
    }
}
