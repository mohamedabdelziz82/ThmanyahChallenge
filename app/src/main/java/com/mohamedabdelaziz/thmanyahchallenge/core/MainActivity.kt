package com.mohamedabdelaziz.thmanyahchallenge.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.mohamedabdelaziz.thmanyahchallenge.core.ui.theme.ThmanyahChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
             ThmanyahChallengeTheme {
                 AppGraph()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ThmanyahChallengeTheme {
        AppGraph()
     }
}