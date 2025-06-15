package com.mohamedabdelaziz.feature.home.presentation.ui.composables
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mohamedabdelaziz.feature.home.presentation.data.UIHomeSection

@Composable
internal fun SquareSection(section: UIHomeSection) {
    LazyRow(contentPadding = PaddingValues(horizontal = 1.dp)) {
        itemsIndexed(
            items = section.content,
            key = { index, content -> "${content.name}_${content.releaseDate}_$index" }
        ) { _, item ->
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start =5.dp ).width(180.dp)) {
                AsyncImage(
                    model = item.avatarUrl,
                    contentDescription = item.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = item.name,
                    color = Color.White,
                    maxLines = 1,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis
                )
            }
         }
    }
}