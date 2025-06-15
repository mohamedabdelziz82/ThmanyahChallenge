package com.mohamedabdelaziz.feature.home.presentation.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mohamedabdelaziz.core.network.utils.formatDuration
import com.mohamedabdelaziz.feature.home.presentation.data.ContentType
import com.mohamedabdelaziz.feature.home.presentation.data.UIHomeContent
import com.mohamedabdelaziz.feature.home.presentation.data.UIHomeSection
import com.mohamedabdelaziz.feature.home.presentation.ui.theme.DarkBuleGray
import com.mohamedabdelaziz.feature.home.presentation.ui.theme.OrangeLight

@Composable
fun BigSquareSection(section: UIHomeSection) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 10.dp),
        modifier = Modifier.testTag("BigSquareSection_LazyRow")
    ){
        itemsIndexed(
            items = section.content,
            key = { index, content -> "${content.name}_${content.releaseDate}_$index" }
        ) { _, item ->
            Card(
                modifier = Modifier
                    .width(350.dp)
                    .padding(5.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = DarkBuleGray)
            ) {
                Box {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .padding(8.dp)
                    ) {
                        AsyncImage(
                            model = item.avatarUrl,
                            contentDescription = item.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )

                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                        ) {
                            Text(
                                text = item.name,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Start,
                            )

                            Row {
                                Text(
                                    text = item.duration.formatDuration(),
                                    color = OrangeLight,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(end = 4.dp)
                                )

                                Text(
                                    text = item.releaseDate,
                                    color = Color.White,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                )
                            }
                        }

                    }
                    if (item is UIHomeContent.PodcastEpisode && section.contentType == ContentType.EPISODE) {
                        DefaultAudioPlayer(
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.BottomEnd),
                            audioUrl = item.audioUrl,
                        )
                    }
                }
            }
        }
    }
}


