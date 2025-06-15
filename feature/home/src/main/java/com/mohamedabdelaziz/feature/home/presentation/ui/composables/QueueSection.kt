package com.mohamedabdelaziz.feature.home.presentation.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mohamedabdelaziz.feature.home.presentation.data.UIHomeContent
import com.mohamedabdelaziz.feature.home.presentation.data.UIHomeSection

@Composable
internal fun QueueSection(section: UIHomeSection) {
    LazyRow(contentPadding = PaddingValues(horizontal = 10.dp)) {
        itemsIndexed(
            items = section.content,
            key = { index, content -> "${content.name}_${content.releaseDate}_$index" }
        ) { _, item ->
            if (item is UIHomeContent.PodcastShow) {
                Box(modifier = Modifier
                    .width(250.dp)
                    .padding(end = 12.dp)) {
                    AsyncImage(
                        model = item.avatarUrl,
                        contentDescription = item.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
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
                        Text(
                            text = item.episodeCount.toString(),
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Start,
                        )
                    }
                }
            }
        }
    }
}