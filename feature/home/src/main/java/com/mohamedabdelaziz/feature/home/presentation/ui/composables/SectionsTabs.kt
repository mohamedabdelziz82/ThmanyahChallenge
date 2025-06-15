package com.mohamedabdelaziz.feature.home.presentation.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mohamedabdelaziz.feature.home.presentation.data.UIHomeSection
import com.mohamedabdelaziz.feature.home.presentation.ui.theme.DarkGray
import com.mohamedabdelaziz.feature.home.presentation.ui.theme.OrangeLight

@Composable
fun SectionsTabs(
    categories: List<UIHomeSection>,
    firstVisibleIndexFromList: Int,
     onCategorySelected: (Int) -> Unit
) {
    val categoryListState = rememberLazyListState()
     LaunchedEffect(firstVisibleIndexFromList) {
        categoryListState.animateScrollToItem(firstVisibleIndexFromList)
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("SectionsTabs")
            .padding(horizontal = 16.dp, vertical = 8.dp),
        state = categoryListState,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(categories) { index, category ->
            SectionsTabItem(
                category = category,
                isSelected = index == firstVisibleIndexFromList,
                onClick = {
                    onCategorySelected(index)
                },
                modifier = Modifier.testTag("SectionsTabItem_$index")

            )
        }
    }
}

@Composable
internal fun SectionsTabItem(
    category: UIHomeSection,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier

) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(
                if (isSelected) OrangeLight else DarkGray
            )
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(
            text = category.name,
            color = if (isSelected) Color.White else Color.LightGray,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        )
    }
}