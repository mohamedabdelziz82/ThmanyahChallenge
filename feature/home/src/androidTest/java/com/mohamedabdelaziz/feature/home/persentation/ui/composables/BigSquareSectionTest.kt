package com.mohamedabdelaziz.feature.home.persentation.ui.composables

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.mohamedabdelaziz.feature.home.presentation.data.ContentType
import com.mohamedabdelaziz.feature.home.presentation.data.LayoutType
import com.mohamedabdelaziz.feature.home.presentation.data.UIHomeContent
import com.mohamedabdelaziz.feature.home.presentation.data.UIHomeSection
import com.mohamedabdelaziz.feature.home.presentation.ui.composables.BigSquareSection
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BigSquareSectionTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }
    private val episode1 = UIHomeContent.PodcastEpisode(
        name = "Test Episode",
        description = "Test Description",
        avatarUrl = "https://example.com/image.jpg",
        duration = 120L,
        language = "en",
        releaseDate = "2024-01-01",
        score = 4.5,
        authorName = "Author",
        episodeId = "ep1",
        episodeType = "type1",
        seasonNumber = 1,
        number = 1,
        audioUrl = "https://example.com/audio.mp3",
        separatedAudioUrl = "",
        chapters = emptyList(),
        podcastId = "p1",
        podcastName = "Podcast Name",
        paidIsEarlyAccess = false,
        paidIsNowEarlyAccess = false,
        paidIsExclusive = false,
        paidIsExclusivePartially = false,
        paidExclusiveStartTime = 0,
        paidExclusivityType = "",
        paidEarlyAccessDate = "",
        paidEarlyAccessAudioUrl = "",
        paidTranscriptUrl = "",
        freeTranscriptUrl = ""
    )

    private val episode2 = episode1.copy(name = "Second Episode", episodeId = "ep2")

    private val uiHomeSection = UIHomeSection(
        content = listOf(episode1, episode2),
        contentType = ContentType.EPISODE,
        name = "Test Section",
        order = 0,
        layoutType = LayoutType.BIG_SQUARE
    )

    @Test
    fun bigSquareSection_displaysMultipleEpisodesCorrectly() {
        composeTestRule.setContent {
            BigSquareSection(section = uiHomeSection)
        }

        composeTestRule.onNodeWithTag("BigSquareSection_LazyRow").assertExists()

        composeTestRule.onNodeWithTag("BigSquareSection_Title_0")
            .assertExists()
            .assertTextEquals("Test Episode")

        composeTestRule.onNodeWithTag("BigSquareSection_Duration_0")
            .assertExists()
            .assertTextEquals("2:00") // assuming 120 seconds formats to 2:00

        composeTestRule.onNodeWithTag("BigSquareSection_Date_0")
            .assertExists()
            .assertTextEquals("2024-01-01")

        composeTestRule.onNodeWithTag("BigSquareSection_Title_1")
            .assertExists()
            .assertTextEquals("Second Episode")

        composeTestRule.onNodeWithTag("BigSquareSection_Date_1")
            .assertExists()
            .assertTextEquals("2024-01-01")
    }
}