package com.mohamedabdelaziz.feature.home.persentation.ui.composables

import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.mohamedabdelaziz.feature.home.presentation.ui.composables.AudioPlayerWithDuration
import com.mohamedabdelaziz.feature.home.presentation.ui.composables.DefaultAudioPlayer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AudioPlayerTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun defaultAudioPlayer_showsPlayIconByDefault() {
        composeTestRule.setContent {
            DefaultAudioPlayer(audioUrl = "test-url")
        }

        composeTestRule
            .onNodeWithTag("DefaultAudioPlayerIcon")
            .assertExists()
            .assertContentDescriptionEquals("Play")
    }

    @Test
    fun audioPlayerWithDuration_showsFormattedDuration() {
        val duration = 90_000L // 1 min 30 sec

        composeTestRule.setContent {
            AudioPlayerWithDuration(audioUrl = "test-url", duration = duration)
        }

        composeTestRule
            .onNodeWithTag("AudioPlayerDurationText")
            .assertExists()
            .assertTextEquals("01:30")
    }
}