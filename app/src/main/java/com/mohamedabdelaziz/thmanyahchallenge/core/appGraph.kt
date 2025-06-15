package com.mohamedabdelaziz.thmanyahchallenge.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mohamedabdelaziz.feature.home.presentation.ui.HomeScreen
import kotlinx.serialization.Serializable


@Composable
internal fun AppGraph(navController: NavHostController = rememberNavController()) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Black,
            darkIcons = false
        )
    }
    NavHost(
        modifier = Modifier
            .background(Color.Black)
            .systemBarsPadding()
            .safeContentPadding(),
        navController = navController,
        startDestination = HomeScreen,
    ) {
        composable<HomeScreen> {
            HomeScreen()
        }
    }
}

@Serializable
object HomeScreen