package com.mohamedabdelaziz.feature.home.presentation.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mohamedabdelaziz.feature.home.presentation.data.ContentType
import com.mohamedabdelaziz.feature.home.presentation.data.UIHomeContent
import com.mohamedabdelaziz.feature.home.presentation.data.UIHomeSection

@Composable
internal fun TwoLineGridSection(section: UIHomeSection) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(section.content.chunked(2)) { columnItems ->
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                columnItems.forEach { item ->
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.Black)
                            .padding(8.dp)
                    ) {
                        AsyncImage(
                            model = item.avatarUrl,
                            contentDescription = item.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )

                        Spacer(modifier = Modifier.width(4.dp))
                        Column {
                            Text(
                                text = item.releaseDate,
                                color = Color.Gray,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = item.name,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            if (item is UIHomeContent.PodcastEpisode && section.contentType == ContentType.EPISODE) {
                                AudioPlayerWithDuration(
                                    audioUrl = item.audioUrl,
                                    duration = item.duration
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}