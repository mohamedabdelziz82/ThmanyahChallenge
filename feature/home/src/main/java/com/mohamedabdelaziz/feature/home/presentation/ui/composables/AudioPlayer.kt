package com.mohamedabdelaziz.feature.home.presentation.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PauseCircleFilled
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.PlayCircleFilled
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mohamedabdelaziz.core.network.utils.formatDuration
import com.mohamedabdelaziz.feature.home.presentation.ui.manipulator.AudioPlayerViewModel
import com.mohamedabdelaziz.feature.home.presentation.ui.theme.DarkBuleGray

@Composable
internal fun DefaultAudioPlayer(
    audioUrl: String,
    modifier: Modifier = Modifier,
    viewModel: AudioPlayerViewModel = hiltViewModel()
) {
    val isThisPlaying by viewModel.isPlaying(audioUrl).collectAsState()

    Icon(
        imageVector = if (isThisPlaying) Icons.Rounded.PauseCircleFilled else Icons.Rounded.PlayCircleFilled,
        contentDescription = if (isThisPlaying) "Pause" else "Play",
        modifier = modifier
            .size(36.dp)
            .testTag("DefaultAudioPlayerIcon")
            .clickable {
                viewModel.initPlayer(audioUrl)
                viewModel.togglePlayPause()
            },
        tint = Color.Gray
    )
}

@Composable
fun AudioPlayerWithDuration(
    audioUrl: String,
    duration: Long,
    modifier: Modifier = Modifier,
    viewModel: AudioPlayerViewModel = hiltViewModel()
) {
    val isThisPlaying by viewModel.isPlaying(audioUrl).collectAsState()
    LaunchedEffect(audioUrl) {
        viewModel.initPlayer(audioUrl)
    }
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = DarkBuleGray)
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .testTag("AudioPlayerWithDurationRow"),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (isThisPlaying) Icons.Rounded.Pause else Icons.Rounded.PlayArrow,
                contentDescription = if (isThisPlaying) "Pause" else "Play",
                modifier = Modifier
                    .size(24.dp)
                    .testTag("AudioPlayerPlayPauseIcon")
                    .clickable {
                        viewModel.initPlayer(audioUrl)
                        viewModel.togglePlayPause()
                    },
                tint = Color.Gray
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = duration.formatDuration(),
                fontSize = 14.sp,
                color = Color.LightGray,
                modifier = Modifier.testTag("AudioPlayerDurationText")
            )
        }
    }
}
